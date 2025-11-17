import { useState } from 'react'

type Props = {
  onSubmit: (v: {name: string; ipoDate: string; initialPrice: number; domain: string}) => void
}

export default function CompanyForm({ onSubmit }: Props){
  const [name, setName] = useState('')
  const [ipoDate, setIpoDate] = useState('')
  const [initialPrice, setInitialPrice] = useState<number>(100)
  const [domain, setDomain] = useState('IT')

  return (
    <form className="card" onSubmit={e=>{e.preventDefault(); onSubmit({name, ipoDate, initialPrice, domain});}}>
      <h3>Create Company</h3>
      <label>Name<input value={name} onChange={e=>setName(e.target.value)} required/></label>
      <label>IPO Date<input type="date" value={ipoDate} onChange={e=>setIpoDate(e.target.value)} required/></label>
      <label>Initial Price<input type="number" step="0.01" value={initialPrice} onChange={e=>setInitialPrice(parseFloat(e.target.value))} required/></label>
      <label>Domain<input value={domain} onChange={e=>setDomain(e.target.value)} required/></label>
      <button type="submit">Create</button>
    </form>
  )
}
