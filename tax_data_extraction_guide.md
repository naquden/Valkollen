# Tax Data Extraction Guide

## How to Extract Real Tax Data from Swedish Budget Documents

### 1. Document Sources
- **SD**: https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf
- **M**: https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf
- **S**: https://www.riksdagen.se/sv/dokument-och-lagar/dokument/motion/sammanhallning-i-en-svar-tid_hb022654/html/
- **V**: https://www.riksdagen.se/sv/dokument-och-lagar/dokument/motion/for-ett-starkare-sverige-klimat-valfard-och_hc021924/html/
- **C**: https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf
- **L**: https://data.riksdagen.se/fil/FB072F0C-5CB0-4A02-A73C-3A50CDF65CAF
- **MP**: https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138

### 2. Key Search Terms (Swedish)
- **Skattepolitik** - Tax policy
- **Skatteförändringar** - Tax changes  
- **Jobbskatteavdrag** - Employment tax deduction
- **Grundavdrag** - Basic deduction
- **Marginalskatt** - Marginal tax rate
- **Kommunalskatt** - Municipal tax
- **Statlig inkomstskatt** - State income tax
- **Skatteinkomster** - Tax revenues

### 3. Data to Extract for Each Party

#### Income Tax Rates
- **Marginalskatt** (Marginal tax rate): Look for percentages like "35%", "25%"
- **Kommunalskatt** (Municipal tax): Usually around 18-20%
- **Total skattesats** (Total tax rate): Combined rate

#### Deductions
- **Jobbskatteavdrag**: Percentage of income (e.g., "10%", "12%")
- **Grundavdrag**: Fixed amount in SEK (e.g., "20 000 kr", "25 000 kr")
- **Barnbidrag** (Child allowance): If applicable
- **Pensionärsavdrag** (Pensioner deduction): If applicable

#### Other Tax Changes
- **Moms** (VAT): Changes to VAT rates
- **Företagsskatt** (Corporate tax): Changes to corporate tax rates
- **Arbetsgivaravgifter** (Employer contributions): Changes to employer taxes

### 4. Current Tax Revenue Data (from budget_data.json)

| Party | Tax Revenue (billion SEK) | Source |
|-------|---------------------------|---------|
| SD | 1,344.97 | [SD Budget 2024](https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf) |
| M | 1,402.15 | [M Budget 2025](https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf) |
| S | 1,369.407 | [S Motion](https://www.riksdagen.se/sv/dokument-och-lagar/dokument/motion/sammanhallning-i-en-svar-tid_hb022654/html/) |
| V | 1,487.7 | [V Motion](https://www.riksdagen.se/sv/dokument-och-lagar/dokument/motion/for-ett-starkare-sverige-klimat-valfard-och_hc021924/html/) |
| C | 1,407.4 | [C Budget](https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf) |
| L | 2,351.8 | [L Motion](https://data.riksdagen.se/fil/FB072F0C-5CB0-4A02-A73C-3A50CDF65CAF) |
| MP | 2,735.5 | [MP Motion](https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138) |

### 5. Extraction Process

1. **Open each PDF/HTML document**
2. **Search for "Skattepolitik" or "Skatteförändringar"**
3. **Look for tables with tax rates and deductions**
4. **Extract specific percentages and amounts**
5. **Update the tax_data.json file with real values**

### 6. Example Structure for Real Data

```json
{
  "partyCode": "SD",
  "partyName": "Sverigedemokraterna (SD)",
  "taxProposals": {
    "incomeTax": {
      "marginalTaxRate": 0.30,  // Extract from document
      "municipalTaxRate": 0.19, // Extract from document
      "totalTaxRate": 0.49,     // Calculate or extract
      "description": "Balanserad skattepolitik med fokus på arbetarklassen"
    },
    "deductions": {
      "jobbskatteavdrag": 0.11, // Extract from document
      "grundavdrag": 22000,     // Extract from document
      "description": "Förbättrat jobbskatteavdrag för arbetare"
    }
  }
}
```

### 7. Next Steps

1. **Start with SD document** - Search for tax policy sections
2. **Extract specific rates** - Look for percentages and amounts
3. **Update tax_data.json** - Replace hardcoded values with real data
4. **Repeat for all parties** - Systematically go through each document
5. **Verify calculations** - Ensure the math adds up correctly

This systematic approach will give you real, accurate tax data from the official government sources!

