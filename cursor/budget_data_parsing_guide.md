# Budget Data Parsing Guide

## Overview
This document outlines the complete process for identifying, extracting, and integrating budget data from Swedish government documents into our Compose Multiplatform application.

## Main Categories Structure

### Current Main Categories (27 total)
Our JSON structure contains 27 main budget categories, each with:
- `id`: Unique identifier (e.g., "government_administration")
- `name`: Swedish display name (e.g., "Rikets styrelse")
- `description`: Brief description
- `displayOrder`: Sort order
- `partyBudgets`: Array of party-specific budget amounts
- `subcategories`: Array of detailed subcategory breakdowns

### Main Categories List:
1. **government_administration** - "Rikets styrelse"
2. **public_economics** - "Samhällsekonomi och finansförvaltning"
3. **tax_customs** - "Skatt, tull och exekution"
4. **municipal_grants** - "Allmänna bidrag till kommuner"
5. **sickness_benefits** - "Ekonomisk trygghet vid sjukdom och funktionsnedsättning"
6. **healthcare** - "Hälsovård, sjukvård och social omsorg"
7. **family_support** - "Ekonomisk trygghet för familjer och barn"
8. **pension_benefits** - "Ekonomisk trygghet vid ålderdom"
9. **integration_equality** - "Integration och jämställdhet"
10. **labor_market** - "Arbetsmarknad och arbetsliv"
11. **study_support** - "Studiestöd"
12. **education** - "Utbildning och universitetsforskning"
13. **international_aid** - "Internationellt bistånd"
14. **migration** - "Migration"
15. **defense** - "Försvar och samhällets krisberedskap"
16. **justice** - "Rättsväsendet"
17. **transport** - "Kommunikationer"
18. **environment** - "Miljö och klimat"
19. **culture** - "Kultur, medier, trossamfund och fritid"
20. **agriculture** - "Jordbruk, skogsbruk och fiske"
21. **industry** - "Näringsliv och regional utveckling"
22. **housing** - "Bostäder och byggande"
23. **research** - "Forskning och utveckling"
24. **energy** - "Energi"
25. **foreign_affairs** - "Utrikesförvaltning"
26. **european_affairs** - "Europeiska unionen"
27. **other** - "Övriga utgifter"

## Subcategory Structure

### JSON Format
Each subcategory follows this structure:
```json
{
  "code": "X:Y",
  "name": "Subcategory Name",
  "partyBudgets": [
    {
      "partyCode": "SD",
      "budgetAmount": 1234.567,
      "sourceUrl": "https://example.com/document.pdf"
    }
  ]
}
```

### Code Format
- `X:Y` where X is the main category number and Y is the subcategory number
- Main categories are numbered 1-27
- Subcategories restart numbering (1, 2, 3...) for each main category

## Document Sources and Parsing

### 1. Riksdagen HTML Documents
**Source**: https://www.riksdagen.se/sv/dokument-och-lagar/dokument/motion/
**Format**: HTML with structured tables
**Key Sections**:
- "Bilaga 2 – Förslag till utgiftsramar för 2024" (Expenditure data)
- "Bilaga 4 – Beräkningar av statens inkomster 2024" (Income data)
- "Utgiftsramar per utgiftsområde" (Subcategory data)

**Parsing Method**:
- Use BeautifulSoup to parse HTML
- Look for table structures with budget data
- Extract "Regeringens förslag" + "Avvikelse från regeringen" = Total budget
- Convert from thousands of SEK to billions of SEK (divide by 1,000,000)

### 2. Regeringen PDF Documents
**Source**: https://www.regeringen.se/contentassets/
**Format**: PDF documents with structured content
**Key Sections**:
- "Utgiftsområde X - [Category Name]" (Main category documents)
- Table of contents with subcategory listings
- Budget tables with numerical data

**Parsing Method**:
- Extract text content from PDF
- Identify subcategory names and codes
- Look for budget amounts in tables
- Convert to appropriate scale (thousands to billions)

### 3. Base Proposition Page
**Source**: https://www.regeringen.se/rattsliga-dokument/proposition/2023/09/prop.-2023241
**Purpose**: Find links to specific category documents
**Key Links**: Look for "Utgiftsområde" in link text

## Data Scale Conversion

### Critical Scale Understanding
- **Riksdagen documents**: Values in thousands of SEK
- **Our JSON data**: Values in billions of SEK (thousands of millions)
- **Conversion factor**: Divide Riksdagen values by 1,000,000

### Example Conversion
```
Riksdagen value: 19,070,363 (thousands of SEK)
Our JSON value: 19.070363 (billions of SEK)
```

## Party Codes

### Current Party Codes
- **S**: Socialdemokraterna
- **SD**: Sverigedemokraterna
- **M**: Moderaterna
- **MP**: Miljöpartiet
- **C**: Centerpartiet
- **V**: Vänsterpartiet
- **L**: Liberalerna
- **KD**: Kristdemokraterna

## Parsing Workflow

### Step 1: Identify Document Type
1. Check if it's HTML (Riksdagen) or PDF (Regeringen)
2. Determine if it contains main category or subcategory data
3. Identify the specific main category it belongs to

### Step 2: Extract Data
1. Parse the document structure
2. Identify budget tables or sections
3. Extract numerical values and descriptions
4. Convert scale if necessary

### Step 3: Integrate into JSON
1. Find the correct main category in BudgetDataJson.kt
2. Add subcategories if they don't exist
3. Add party-specific budget data
4. Include proper source URLs

### Step 4: Validate
1. Check for linting errors
2. Compile the project
3. Verify data consistency

## Common Challenges and Solutions

### 1. Scale Confusion
**Problem**: Mixing up thousands vs billions
**Solution**: Always verify against existing data scale

### 2. Missing Categories
**Problem**: Not all 27 categories are present in every document
**Solution**: Cross-reference with complete category list

### 3. Subcategory Mapping
**Problem**: Subcategories don't always map 1:1 with main categories
**Solution**: Use document structure and context to determine correct mapping

### 4. Number Format Issues
**Problem**: Different minus signs (− vs -) and special characters
**Solution**: Normalize text before parsing numbers

## File Locations

### Main Data File
- `composeApp/src/commonMain/kotlin/se/atte/partier/data/BudgetDataJson.kt`

### Parsing Scripts
- `extract_subcategories_final.py` - Python script for HTML parsing
- `subcategories_final.json` - Output from parsing script

### Source Documents
- `socialdemokraterna-2025.html` - Socialdemokraterna budget motion
- Various PDF documents from regeringen.se

## Current Status

### Completed
- ✅ Socialdemokraterna main category budgets (all 27 categories)
- ✅ Socialdemokraterna income data (6 categories)
- ✅ Socialdemokraterna subcategory data (342 subcategories)
- ✅ Sverigedemokraterna subcategory data for "Samhällsekonomi och finansförvaltning" (18 subcategories)
- ✅ Sverigedemokraterna subcategory data for "Rikets styrelse" (20 subcategories)
- ✅ Sverigedemokraterna subcategory data for "Skatt, tull och exekution" (8 subcategories)

### Pending
- 🔄 Sverigedemokraterna subcategory data for remaining 24 main categories
- 🔄 Other parties' subcategory data
- 🔄 Income data for other parties

## Next Steps

1. **Identify remaining PDF documents** from the base proposition page
2. **Extract subcategory data** for each main category
3. **Add party-specific budgets** for each subcategory
4. **Validate and test** the complete dataset
5. **Implement UI components** to display the detailed data

## Tools and Dependencies

### Python Scripts
- `BeautifulSoup` for HTML parsing
- `json` for data formatting
- `re` for text processing

### Kotlin/Compose
- `BudgetDataJson.kt` for data storage
- `Party.kt` for party definitions
- `HeroIcons.kt` for UI icons

### Compilation
- `./gradlew compileKotlinWasmJs` to test changes
- `read_lints` to check for errors

## Notes

- Always maintain data consistency across all parties
- Use proper source URLs for traceability
- Test compilation after each major change
- Keep backup of working versions before major updates
- Document any new parsing patterns or edge cases discovered
