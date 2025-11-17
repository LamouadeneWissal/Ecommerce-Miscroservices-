import { ChangeEvent, useEffect, useMemo, useState } from 'react'
import { Company } from '../types'
import { createCompany, deleteCompany, listCompanies, listCompaniesByDomain, updateCompanyPrice } from '../services/api'
import CompanyForm from '../components/CompanyForm'

export default function CompaniesPage(){
  const [companies, setCompanies] = useState<Company[]>([])
  const [domainFilter, setDomainFilter] = useState<string>('')
  const [loading, setLoading] = useState<boolean>(true)
  const [priceEdit, setPriceEdit] = useState<{id: number, price: number}|null>(null)

  const load = async () => {
    setLoading(true)
    try{
      const data = domainFilter ? await listCompaniesByDomain(domainFilter) : await listCompanies()
      setCompanies(data)
    } finally { setLoading(false) }
  }

  useEffect(()=>{ load() }, [domainFilter])

  const onCreate = async (c: {name: string; ipoDate: string; initialPrice: number; domain: string}) => {
    await createCompany(c)
    setDomainFilter('')
    await load()
  }

  const onDelete = async (id: number) => {
    await deleteCompany(id)
    await load()
  }

  const onUpdatePrice = async () => {
    if(!priceEdit) return
    await updateCompanyPrice(priceEdit.id, priceEdit.price)
    setPriceEdit(null)
    await load()
  }

  const domains = useMemo<string[]>(()=> Array.from(new Set(companies.map((c: Company)=>c.domain))), [companies])

  return (
    <div>
      <h2>Companies</h2>
      <div className="row">
        <CompanyForm onSubmit={onCreate} />
        <div className="card">
          <h3>Filter</h3>
          <label>Domain
            <select value={domainFilter} onChange={(e: ChangeEvent<HTMLSelectElement>)=>setDomainFilter(e.target.value)}>
              <option value="">All</option>
              {domains.map((d: string)=> <option key={d} value={d}>{d}</option>)}
            </select>
          </label>
        </div>
      </div>

      {loading? <p>Loading...</p> : (
        <table className="table">
          <thead>
            <tr>
              <th>ID</th><th>Name</th><th>IPO Date</th><th>Domain</th><th>Current Price</th><th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {companies.map(c=> (
              <tr key={c.id}>
                <td>{c.id}</td>
                <td>{c.name}</td>
                <td>{c.ipoDate}</td>
                <td>{c.domain}</td>
                <td>{c.currentPrice?.toFixed?.(2) ?? '-'}</td>
                <td>
                  <button onClick={()=>setPriceEdit({id: c.id!, price: c.currentPrice ?? 0})}>Update Price</button>
                  <button className="danger" onClick={()=>onDelete(c.id!)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {priceEdit && (
        <div className="modal">
          <div className="modal-content">
            <h3>Update Price for #{priceEdit.id}</h3>
            <input type="number" step="0.01" value={priceEdit.price} onChange={e=>setPriceEdit({id: priceEdit.id, price: parseFloat(e.target.value)})} />
            <div className="modal-actions">
              <button onClick={onUpdatePrice}>Save</button>
              <button className="secondary" onClick={()=>setPriceEdit(null)}>Cancel</button>
            </div>
          </div>
        </div>
      )}
    </div>
  )
}
