import { useEffect, useState } from 'react'
import { Company } from '../types'

type Props = {
  companies: Company[]
  onSubmit: (v: {date?: string; openValue: number; highValue: number; lowValue: number; closeValue: number; volume: number; companyId: number}) => void
}

export default function StockForm({ companies, onSubmit }: Props){
  const [companyId, setCompanyId] = useState<number>(0)
  const [date, setDate] = useState<string>('')
  const [openValue, setOpenValue] = useState<number>(0)
  const [highValue, setHighValue] = useState<number>(0)
  const [lowValue, setLowValue] = useState<number>(0)
  const [closeValue, setCloseValue] = useState<number>(0)
  const [volume, setVolume] = useState<number>(0)

  useEffect(()=>{
    if(companies.length && !companyId) setCompanyId(companies[0].id!)
  },[companies])

  return (
    <form className="card" onSubmit={e=>{e.preventDefault(); onSubmit({date: date||undefined, openValue, highValue, lowValue, closeValue, volume, companyId});}}>
      <h3>Add Stock Quote</h3>
      <label>Company
        <select value={companyId} onChange={e=>setCompanyId(parseInt(e.target.value))}>
          {companies.map(c=> <option key={c.id} value={c.id}>{c.name}</option>)}
        </select>
      </label>
      <label>Date/Time (optional)
        <input type="datetime-local" value={date} onChange={e=>setDate(e.target.value)} />
      </label>
      <label>Open<input type="number" step="0.01" value={openValue} onChange={e=>setOpenValue(parseFloat(e.target.value))} required/></label>
      <label>High<input type="number" step="0.01" value={highValue} onChange={e=>setHighValue(parseFloat(e.target.value))} required/></label>
      <label>Low<input type="number" step="0.01" value={lowValue} onChange={e=>setLowValue(parseFloat(e.target.value))} required/></label>
      <label>Close<input type="number" step="0.01" value={closeValue} onChange={e=>setCloseValue(parseFloat(e.target.value))} required/></label>
      <label>Volume<input type="number" step="1" value={volume} onChange={e=>setVolume(parseInt(e.target.value))} required/></label>
      <button type="submit">Add</button>
    </form>
  )
}
