package org.sid.aichatbot.bot;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.sid.aichatbot.feign.CompanyApi;
import org.sid.aichatbot.feign.StockApi;
import org.sid.aichatbot.openai.OpenAIClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AiTelegramBot extends TelegramLongPollingBot {
    private final CompanyApi companyApi;
    private final StockApi stockApi;
    private final OpenAIClient openAIClient;
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() { return botUsername; }

    @Override
    public String getBotToken() { return botToken; }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() == null || update.getMessage().getText() == null) return;
        String text = update.getMessage().getText().trim();
        Long chatId = update.getMessage().getChatId();

        try {
            if (text.equalsIgnoreCase("/start") || text.equalsIgnoreCase("/help")) {
                reply(chatId, "Commands:\n" +
                        "/company_add {json}\n" +
                        "/company_get {id}\n" +
                        "/company_price {id} {price}\n" +
                        "/companies\n" +
                        "/companies_domain {domain}\n" +
                        "/stock_add {json}\n" +
                        "/stock_get {id}\n" +
                        "/stocks_company {companyId}");
                return;
            }
            if (text.startsWith("/company_add")) {
                String json = text.replaceFirst("/company_add", "").trim();
                Map<?,?> m = mapper.readValue(json, Map.class);
                var res = companyApi.create(new CompanyApi.CreateCompany(
                        (String)m.get("name"),
                        LocalDate.parse((String)m.get("ipoDate")),
                        Double.valueOf(m.get("initialPrice").toString()),
                        (String)m.get("domain")
                ));
                reply(chatId, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res));
                return;
            }
            if (text.startsWith("/company_get")) {
                Long id = Long.parseLong(text.replaceFirst("/company_get", "").trim());
                var res = companyApi.get(id);
                reply(chatId, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res));
                return;
            }
            if (text.startsWith("/company_price")) {
                String[] parts = text.split("\\s+");
                Long id = Long.parseLong(parts[1]);
                Double price = Double.parseDouble(parts[2]);
                var res = companyApi.updatePrice(id, new CompanyApi.UpdatePrice(price));
                reply(chatId, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res));
                return;
            }
            if (text.equals("/companies")) {
                var res = companyApi.list();
                reply(chatId, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res));
                return;
            }
            if (text.startsWith("/companies_domain")) {
                String domain = text.replaceFirst("/companies_domain", "").trim();
                var res = companyApi.listByDomain(domain);
                reply(chatId, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res));
                return;
            }
            if (text.startsWith("/stock_add")) {
                String json = text.replaceFirst("/stock_add", "").trim();
                Map<?,?> m = mapper.readValue(json, Map.class);
                LocalDateTime date = m.get("date")!=null? LocalDateTime.parse((String)m.get("date")) : null;
                var res = stockApi.create(new StockApi.CreateStock(
                        date,
                        Double.valueOf(m.get("openValue").toString()),
                        Double.valueOf(m.get("highValue").toString()),
                        Double.valueOf(m.get("lowValue").toString()),
                        Double.valueOf(m.get("closeValue").toString()),
                        Long.valueOf(m.get("volume").toString()),
                        Long.valueOf(m.get("companyId").toString())
                ));
                reply(chatId, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res));
                return;
            }
            if (text.startsWith("/stock_get")) {
                Long id = Long.parseLong(text.replaceFirst("/stock_get", "").trim());
                var res = stockApi.get(id);
                reply(chatId, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res));
                return;
            }
            if (text.startsWith("/stocks_company")) {
                Long companyId = Long.parseLong(text.replaceFirst("/stocks_company", "").trim());
                var res = stockApi.byCompany(companyId);
                reply(chatId, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res));
                return;
            }

            // Fallback: send to LLM for assistance formatting
            String sys = "You are a helpful assistant for a stock market bot. If the user's message resembles an action, respond succinctly with the exact command the user should run, using the commands /company_add, /company_get, /company_price, /companies, /companies_domain, /stock_add, /stock_get, /stocks_company. Otherwise answer briefly.";
            openAIClient.chat(sys, text).subscribe(ans -> reply(chatId, ans));
        } catch (Exception e) {
            reply(chatId, "Error: " + e.getMessage());
        }
    }

    private void reply(Long chatId, String text) {
        try {
            execute(SendMessage.builder().chatId(chatId.toString()).text(text).build());
        } catch (Exception ignored) { }
    }
}
