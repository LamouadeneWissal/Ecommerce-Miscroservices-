export type Company = {
  id?: number
  name: string
  ipoDate: string
  currentPrice?: number
  domain: string
}

export type Stock = {
  id?: number
  date?: string
  openValue: number
  highValue: number
  lowValue: number
  closeValue: number
  volume: number
  companyId: number
}
