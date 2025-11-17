import { ChangeEvent, useEffect, useMemo, useState } from 'react'
import { Company, Stock } from '../types'
import { createStock, deleteStock, listStocks, listStocksByCompany } from '../services/api'
import { listCompanies } from '../services/api'
import StockForm from '../components/StockForm'

export default function StocksPage(){
  const [companies, setCompanies] = useState<Company[]>([])
  const [stocks, setStocks] = useState<Stock[]>([])
  const [companyFilter, setCompanyFilter] = useState<number|''>('')
  const [loading, setLoading] = useState<boolean>(true)

  const companyById = useMemo(()=>{
    const map = new Map<number, Company>()
    companies.forEach((c: Company)=>{ if(c.id != null) map.set(c.id, c) })
    return map
  }, [companies])

  const loadCompanies = async () => {
    const data = await listCompanies()
    setCompanies(data)
  }

  const loadStocks = async () => {
    setLoading(true)
    try{
      if(companyFilter){
        const data = await listStocksByCompany(Number(companyFilter))
        setStocks(data)
      } else {
        const data = await listStocks()
        setStocks(data)
      }
    } finally { setLoading(false) }
  }

  useEffect(()=>{ loadCompanies() }, [])
  useEffect(()=>{ loadStocks() }, [companyFilter])

  const onCreate = async (s: {date?: string; openValue: number; highValue: number; lowValue: number; closeValue: number; volume: number; companyId: number}) => {
    await createStock(s)
    await loadStocks()
  }

  const onDelete = async (id: number) => {
    await deleteStock(id)
    await loadStocks()
  }

  return (
    <div>
      <h2>Stocks</h2>
      <div className="row">
        <StockForm companies={companies} onSubmit={onCreate} />
        <div className="card">
          <h3>Filter</h3>
          <label>Company
            <select value={companyFilter} onChange={(e: ChangeEvent<HTMLSelectElement>)=> setCompanyFilter(e.target.value ? Number(e.target.value) : '')}>
              <option value="">All</option>
              {companies.map((c: Company)=> <option key={c.id} value={c.id}>{c.name}</option>)}
            </select>
          </label>
        </div>
      </div>

      {loading? <p>Loading...</p> : (
        <table className="table">
          <thead>
            <tr>
              <th>ID</th><th>Company</th><th>Date</th><th>Open</th><th>High</th><th>Low</th><th>Close</th><th>Volume</th><th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {stocks.map((s)=> (
              <tr key={s.id}>
                <td>{s.id}</td>
                <td>{companyById.get(s.companyId)?.name ?? s.companyId}</td>
                <td>{s.date || '-'}</td>
                <td>{s.openValue.toFixed(2)}</td>
                <td>{s.highValue.toFixed(2)}</td>
                <td>{s.lowValue.toFixed(2)}</td>
                <td>{s.closeValue.toFixed(2)}</td>
                <td>{s.volume}</td>
                <td>
                  <button className="danger" onClick={()=> onDelete(s.id!)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  )
}
