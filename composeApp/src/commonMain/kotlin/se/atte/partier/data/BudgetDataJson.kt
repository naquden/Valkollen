package se.atte.partier.data

object BudgetDataJson {
    const val JSON_CONTENT = """
{
  "year": 2025,
  "totalBudget": 1428.4,
  "budgetCategories": [
    {
      "id": "government_administration",
      "name": "Rikets styrelse",
      "description": "Regeringens administration och statsrådsberedningen",
      "displayOrder": 1,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 19.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 20.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 20.3,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 19.6,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 20.4,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "public_economics",
      "name": "Samhällsekonomi och finansförvaltning",
      "description": "Ekonomisk politik och finansförvaltning",
      "displayOrder": 2,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 20.9,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 21.7,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 21.8,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 21.7,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 21.9,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "tax_customs",
      "name": "Skatt, tull och exekution",
      "description": "Skattemyndigheter, tullverket och kronofogdemyndigheten",
      "displayOrder": 3,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 16.9,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 14.8,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 14.8,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 14.6,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 14.9,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "municipal_grants",
      "name": "Allmänna bidrag till kommuner",
      "description": "Finansierar välfärdstjänster som skola, sjukvård och social omsorg",
      "displayOrder": 25,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 174.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 173.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 174.1,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 178.4,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 179.8,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "sickness_benefits",
      "name": "Ekonomisk trygghet vid sjukdom och funktionsnedsättning",
      "description": "Sjukförsäkring, ersättningar och stöd till personer med funktionsnedsättning",
      "displayOrder": 10,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 116.9,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 123.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 123.1,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 122.3,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 139.1,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "healthcare",
      "name": "Hälsovård, sjukvård och social omsorg",
      "description": "Allmän hälso- och sjukvård samt social omsorg",
      "displayOrder": 9,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 110.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 120.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 132.5,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 120.6,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 126.5,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "family_support",
      "name": "Ekonomisk trygghet för familjer och barn",
      "description": "Barnbidrag, föräldrapenning och andra familjestöd",
      "displayOrder": 12,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 106.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 104.5,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 104.5,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 103.2,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 118.9,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "pension_benefits",
      "name": "Ekonomisk trygghet vid ålderdom",
      "description": "Pensioner och ålderspension",
      "displayOrder": 11,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 60.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 62.9,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 62.9,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 62.9,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 66.2,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "integration_equality",
      "name": "Integration och jämställdhet",
      "description": "Integration, jämställdhet och diskrimineringsfrågor",
      "displayOrder": 13,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 4.0,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 6.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 9.3,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 6.8,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 7.8,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "labor_market",
      "name": "Arbetsmarknad och arbetsliv",
      "description": "Arbetsmarknadspolitik, arbetsförmedling och arbetslivsutveckling",
      "displayOrder": 14,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 92.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 93.5,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 95.5,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 78.1,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 112.2,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "study_support",
      "name": "Studiestöd",
      "description": "Studiebidrag och studielån",
      "displayOrder": 15,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 30.5,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 33.8,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 36.1,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 33.7,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 34.4,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "education",
      "name": "Utbildning och universitetsforskning",
      "description": "Grundskola, gymnasium, universitet och forskning",
      "displayOrder": 16,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 99.5,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 103.8,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 109.3,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 108.9,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 109.2,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "international_aid",
      "name": "Internationellt bistånd",
      "description": "Utvecklingsbistånd och humanitärt bistånd",
      "displayOrder": 7,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 48.6,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 44.5,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 57.0,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 46.2,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 55.1,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "migration",
      "name": "Migration",
      "description": "Migrationsverket och asylprocessen",
      "displayOrder": 8,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 13.8,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 11.9,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 12.3,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 11.8,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 12.0,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "defense",
      "name": "Försvar och samhällets krisberedskap",
      "description": "Försvarsmakt, civilförsvar och säkerhetspolitik",
      "displayOrder": 6,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 126.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 169.7,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 170.6,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 170.0,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 169.7,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "justice",
      "name": "Rättsväsendet",
      "description": "Domstolar, åklagare och kriminalvård",
      "displayOrder": 4,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 76.0,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 86.8,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 88.4,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 87.2,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 118.9,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "transport",
      "name": "Kommunikationer",
      "description": "Vägar, järnvägar och kollektivtrafik",
      "displayOrder": 22,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 82.9,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 94.4,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 133.8,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 95.4,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 109.9,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "environment",
      "name": "Klimat, miljö och natur",
      "description": "Miljöskydd, klimatåtgärder och naturvård",
      "displayOrder": 20,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 19.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 16.4,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 40.6,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 21.4,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 31.9,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "culture",
      "name": "Kultur, medier, trossamfund och fritid",
      "description": "Kulturstöd, public service och idrott",
      "displayOrder": 17,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 16.6,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 16.9,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 19.8,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 17.6,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 19.2,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "foreign_affairs",
      "name": "Internationell samverkan",
      "description": "Diplomati, utvecklingssamarbete och internationella organisationer",
      "displayOrder": 5,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 2.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 2.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 2.4,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 2.3,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 2.3,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "agriculture",
      "name": "Areella näringar, landsbygd och livsmedel",
      "description": "Lantbruksstöd, skogsbruk och fiskeripolitik",
      "displayOrder": 23,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 24.0,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 21.7,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 25.4,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 22.0,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 24.7,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "business_sector",
      "name": "Näringsliv",
      "description": "Näringslivspolitik och företagsstöd",
      "displayOrder": 24,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 9.6,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 8.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 8.7,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 8.3,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 8.8,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "energy",
      "name": "Energi",
      "description": "Energipolitik, näringslivsstöd och regional utveckling",
      "displayOrder": 21,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 5.6,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 6.6,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 20.0,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 7.0,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 10.9,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "housing",
      "name": "Bostadsförsörjning och byggande",
      "description": "Bostadspolitik, hyresreglering och byggande",
      "displayOrder": 18,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 6.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 3.2,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 13.8,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 3.3,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 10.1,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "regional_development",
      "name": "Regional utveckling",
      "description": "Regional utveckling och landsbygdspolitik",
      "displayOrder": 19,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 3.9,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 4.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 4.4,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 4.3,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 4.5,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "debt_interest",
      "name": "Statsskuldsräntor m.m.",
      "description": "Räntor på statsskuld och finansiella kostnader",
      "displayOrder": 26,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 20.5,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 28.8,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 28.8,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 28.8,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 28.8,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    },
    {
      "id": "eu_fee",
      "name": "Avgiften till Europeiska unionen",
      "description": "Sveriges avgift till Europeiska unionen",
      "displayOrder": 27,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 40.8,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 47.8,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 47.8,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 47.8,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        },
        {
          "partyCode": "V",
          "budgetAmount": 47.8,
          "sourceUrl": "https://data.riksdagen.se/fil/06D97284-41A0-430E-A8FA-52B2DC39D86C"
        }
      ]
    }
  ],
  "incomeCategories": [
    {
      "id": "tax_income",
      "name": "Statens skatteinkomster",
      "description": "Inkomster från skatter och avgifter",
      "displayOrder": 1,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 1344.97,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 1402.15,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 2735.5,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 1407.4,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        }
      ]
    },
    {
      "id": "activity_income",
      "name": "Inkomster av statens verksamhet",
      "description": "Inkomster från statliga verksamheter",
      "displayOrder": 2,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 47.89,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 47.84,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 47.8,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 48.3,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        }
      ]
    },
    {
      "id": "property_sales",
      "name": "Inkomster av försåld egendom",
      "description": "Inkomster från försäljning av statlig egendom",
      "displayOrder": 3,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 5.0,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 5.0,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 5.0,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 5.0,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        }
      ]
    },
    {
      "id": "loan_repayments",
      "name": "Återbetalning av lån",
      "description": "Återbetalningar av utgivna lån",
      "displayOrder": 4,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 0.61,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 0.56,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 0.6,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 0.6,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        }
      ]
    },
    {
      "id": "calculated_income",
      "name": "Kalkylmässiga inkomster",
      "description": "Beräknade inkomster och justeringar",
      "displayOrder": 5,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 22.16,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 23.92,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 24.0,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 23.9,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        }
      ]
    },
    {
      "id": "eu_grants",
      "name": "Bidrag m.m. från EU",
      "description": "Bidrag och stöd från Europeiska unionen",
      "displayOrder": 6,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 48.67,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 48.77,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 48.8,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 48.8,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        }
      ]
    },
    {
      "id": "tax_system_adjustments",
      "name": "Avräkningar m.m. i anslutning till skattesystemet",
      "description": "Justeringar och avräkningar i skattesystemet",
      "displayOrder": 7,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": -145.05,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": -154.37,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": -150.0,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": -154.4,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        }
      ]
    },
    {
      "id": "expense_credits",
      "name": "Utgifter som redovisas som krediteringar på skattekonto",
      "description": "Utgifter som redovisas som krediteringar på skattekonto",
      "displayOrder": 8,
      "partyBudgets": [
        {
          "partyCode": "SD",
          "budgetAmount": 0.0,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyCode": "M",
          "budgetAmount": 0.0,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        },
        {
          "partyCode": "MP",
          "budgetAmount": 0.0,
          "sourceUrl": "https://data.riksdagen.se/fil/675F19E7-DA8F-49B8-977C-A4A8F3D25138"
        },
        {
          "partyCode": "C",
          "budgetAmount": 0.0,
          "sourceUrl": "https://www.centerpartiet.se/download/18.6b62049a191e3e87aab3fd0b/1727876611373/Centerpartiets%20budgetmotion%20f%C3%B6r%202025%20webb%20v3.pdf"
        }
      ]
    }
  ]
}
"""
}



/*
    These are the 27 Swedish government expenditure areas, these should never change or be modified
    Utgiftsområde 1 Rikets styrelse
    Utgiftsområde 2 Samhällsekonomi och finansförvaltning
    Utgiftsområde 3 Skatt, tull och exekution
    Utgiftsområde 4 Rättsväsendet
    Utgiftsområde 5 Internationell samverkan
    Utgiftsområde 6 Försvar och samhällets krisberedskap
    Utgiftsområde 7 Internationellt bistånd
    Utgiftsområde 8 Migration
    Utgiftsområde 9 Hälsovård, sjukvård och social omsorg
    Utgiftsområde 10 Ekonomisk trygghet vid sjukdom och funktionsnedsättning
    Utgiftsområde 11 Ekonomisk trygghet vid ålderdom
    Utgiftsområde 12 Ekonomisk trygghet för familjer och barn
    Utgiftsområde 13 Integration och jämställdhet
    Utgiftsområde 14 Arbetsmarknad och arbetsliv
    Utgiftsområde 15 Studiestöd
    Utgiftsområde 16 Utbildning och universitetsforskning
    Utgiftsområde 17 Kultur, medier, trossamfund och fritid
    Utgiftsområde 18 Samhällsplanering, bostadsförsörjning och byggande samt konsumentpolitik
    Utgiftsområde 19 Regional utveckling
    Utgiftsområde 20 Klimat, miljö och natur
    Utgiftsområde 21 Energi
    Utgiftsområde 22 Kommunikationer
    Utgiftsområde 23 Areella näringar, landsbygd och livsmedel
    Utgiftsområde 24 Näringsliv
    Utgiftsområde 25 Allmänna bidrag till kommuner
    Utgiftsområde 26 Statsskuldsräntor m.m.
    Utgiftsområde 27 Avgiften till Europeiska unionen

    Inkomsttyp 1 Statens skatteinkomster
    Inkomsttyp 2 Inkomster av statens verksamhet
    Inkomsttyp 3 Inkomster av försåld egendom
    Inkomsttyp 4 Återbetalning av lån
    Inkomsttyp 5 Kalkylmässiga inkomster
    Inkomsttyp 6 Bidrag m.m. från EU
    Inkomsttyp 7 Avräkningar m.m. i anslutning till skattesystemet
    Inkomsttyp 8 Utgifter som redovisas som krediteringar på skattekonto
*/