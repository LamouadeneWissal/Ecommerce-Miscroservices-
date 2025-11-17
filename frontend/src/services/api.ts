import axios from 'axios'

const api = axios.create({
  baseURL: '/api',
})

// Companies
export const listCompanies = () => api.get('/companies').then(r => r.data)
export const getCompany = (id: number) => api.get(`/companies/${id}`).then(r => r.data)
export const createCompany = (c: {name: string; ipoDate: string; initialPrice: number; domain: string}) =>
  api.post('/companies', c).then(r => r.data)
export const deleteCompany = (id: number) => api.delete(`/companies/${id}`)
export const updateCompanyPrice = (id: number, price: number) =>
  api.put(`/companies/${id}/price`, { price }).then(r => r.data)
export const listCompaniesByDomain = (domain: string) =>
  api.get(`/companies/domain/${encodeURIComponent(domain)}`).then(r => r.data)

// Stocks
export const listStocks = () => api.get('/stocks').then(r => r.data)
export const getStock = (id: number) => api.get(`/stocks/${id}`).then(r => r.data)
export const listStocksByCompany = (companyId: number) => api.get(`/stocks/company/${companyId}`).then(r => r.data)
export const createStock = (s: {
  date?: string
  openValue: number
  highValue: number
  lowValue: number
  closeValue: number
  volume: number
  companyId: number
}) => api.post('/stocks', s).then(r => r.data)
export const deleteStock = (id: number) => api.delete(`/stocks/${id}`)

export default api
