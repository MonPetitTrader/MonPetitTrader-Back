import pandas as pd
import time

headers = {    
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:132.0) Gecko/20100101 Firefox/132.0",
    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
    "Accept-Language": "fr,fr-FR;q=0.8,en-US;q=0.5,en;q=0.3",
    "Alt-Used": "stockanalysis.com",
    "Upgrade-Insecure-Requests": "1",
    "Sec-Fetch-Dest": "document",
    "Sec-Fetch-Mode": "navigate",
    "Sec-Fetch-Site": "none",
    "Sec-Fetch-User": "?1",
    "Priority": "u=0, i"
}

symbols = ["BRK.A", "BRK.B", "TSM", "WMT", "JPM", "LLY", "V", "UNH", "XOM", "ORCL", "MA", "NVO", "PG", "HD", "JNJ", "BAC", "CRM", "ABBV", "CVX", "SAP", "KO", "MRK", "WFC", "TM", "BX", "ACN", "MS", "NOW", "MCD", "NVS", "SHEL", "DIS", "PM", "ABT", "BABA", "AXP", "IBM", "GS", "GE", "TMO", "CAT", "VZ", "RY", "DHR", "T", "HSBC", "BLK", "RTX", "HDB", "NEE", "SPGI", "PGR", "SYK", "LOW", "SCHW", "UBER", "UL", "ETN", "UNP", "PFE", "TTE", "MUFG", "PLTR", "KKR", "SHOP", "TJX", "BSX", "BHP", "COP", "C", "LMT", "FI", "ANET", "CB", "SONY", "BMY", "UPS", "BUD", "NKE", "MMC", "DE", "MDT", "BA", "PLD", "RIO", "IBN", "UBS", "TD", "SO", "MO", "APO", "DELL", "AMT", "SHW", "SMFG", "ELV", "ENB", "SPOT", "TT", "ICE"] 

for cie in symbols: 
    data = pd.read_html(f'https://stockanalysis.com/stocks/{cie}/history/', storage_options=headers)
    df = data[0]
    df.to_csv(f"{cie}.csv", index=False)
    print(f"{cie} done")
    time.sleep(1)