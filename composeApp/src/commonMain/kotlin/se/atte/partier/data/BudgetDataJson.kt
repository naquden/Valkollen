package se.atte.partier.data

object BudgetDataJson {
    const val JSON_CONTENT = """
{
  "year": 2025,
  "totalBudget": 1428.4,
  "budgetCategories": [
    {
      "id": "municipal_grants",
      "name": "Allmänna bidrag till kommuner",
      "description": "Finansierar välfärdstjänster som skola, sjukvård och social omsorg",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 174.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": 173.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    },
    {
      "id": "sickness_benefits",
      "name": "Ekonomisk trygghet vid sjukdom och funktionsnedsättning",
      "description": "Sjukförsäkring, ersättningar och stöd till personer med funktionsnedsättning",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 116.9,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": 123.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    },
    {
      "id": "healthcare",
      "name": "Hälsovård, sjukvård och social omsorg",
      "description": "Allmän hälso- och sjukvård samt social omsorg",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 110.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": 120.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    },
    {
      "id": "family_support",
      "name": "Ekonomisk trygghet för familjer och barn",
      "description": "Barnbidrag, föräldrapenning och andra familjestöd",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 106.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": 104.5,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    },
    {
      "id": "labor_market",
      "name": "Arbetsmarknad och arbetsliv",
      "description": "Arbetsmarknadspolitik, arbetsförmedling och arbetslivsutveckling",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 92.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": 93.5,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    },
    {
      "id": "education",
      "name": "Utbildning och universitetsforskning",
      "description": "Grundskola, gymnasium, universitet och forskning",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 99.5,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": 103.8,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    },
    {
      "id": "migration",
      "name": "Migration",
      "description": "Migrationsverket och asylprocessen",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 13.8,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": 11.9,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    },
    {
      "id": "defense",
      "name": "Försvar och samhällets krisberedskap",
      "description": "Försvarsmakt, civilförsvar och säkerhetspolitik",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 126.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": 169.7,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    },
    {
      "id": "justice",
      "name": "Rättsväsendet",
      "description": "Domstolar, åklagare och kriminalvård",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 76.0,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        }
      ]
    },
    {
      "id": "transport",
      "name": "Kommunikationer",
      "description": "Vägar, järnvägar och kollektivtrafik",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 82.9,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        }
      ]
    },
    {
      "id": "environment",
      "name": "Klimat, miljö och natur",
      "description": "Miljöskydd, klimatåtgärder och naturvård",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 19.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        }
      ]
    },
    {
      "id": "culture",
      "name": "Kultur, medier, trossamfund och fritid",
      "description": "Kulturstöd, public service och idrott",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 16.6,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        }
      ]
    },
    {
      "id": "foreign_affairs",
      "name": "Internationell samverkan",
      "description": "Diplomati, utvecklingssamarbete och internationella organisationer",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 2.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        }
      ]
    },
    {
      "id": "agriculture",
      "name": "Areella näringar, landsbygd och livsmedel",
      "description": "Lantbruksstöd, skogsbruk och fiskeripolitik",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 24.0,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        }
      ]
    },
    {
      "id": "energy",
      "name": "Energi, näringsliv och regional utveckling",
      "description": "Energipolitik, näringslivsstöd och regional utveckling",
      "partyBudgets": []
    },
    {
      "id": "housing",
      "name": "Bostadsförsörjning och byggande",
      "description": "Bostadspolitik, hyresreglering och byggande",
      "partyBudgets": []
    },
    {
      "id": "research",
      "name": "Forskning och utveckling",
      "description": "Vetenskaplig forskning och teknisk utveckling",
      "partyBudgets": []
    },
    {
      "id": "employment",
      "name": "Arbetsmarknads- och utbildningsministeriet",
      "description": "Arbetsmarknadspolitik och utbildningsministeriet",
      "partyBudgets": []
    },
    {
      "id": "finance",
      "name": "Finansförvaltning",
      "description": "Skatter, tullar och ekonomisk förvaltning",
      "partyBudgets": []
    },
    {
      "id": "government",
      "name": "Statsrådsberedningen",
      "description": "Regeringskansliet och statsrådsberedningen",
      "partyBudgets": []
    },
    {
      "id": "parliament",
      "name": "Riksdagen",
      "description": "Riksdagens verksamhet och administration",
      "partyBudgets": []
    },
    {
      "id": "courts",
      "name": "Domstolsväsendet",
      "description": "Högre domstolar och specialdomstolar",
      "partyBudgets": []
    },
    {
      "id": "ombudsman",
      "name": "Justitieombudsmannen",
      "description": "Justitieombudsmannen och JO",
      "partyBudgets": []
    },
    {
      "id": "audit",
      "name": "Riksrevisionen",
      "description": "Riksrevisionen och statskontroll",
      "partyBudgets": []
    },
    {
      "id": "elections",
      "name": "Valmyndigheten",
      "description": "Valmyndigheten och valadministration",
      "partyBudgets": []
    },
    {
      "id": "data_protection",
      "name": "Integritetsskyddsmyndigheten",
      "description": "Integritetsskyddsmyndigheten och dataskydd",
      "partyBudgets": []
    },
    {
      "id": "competition",
      "name": "Konkurrensverket",
      "description": "Konkurrensverket och konkurrenspolitik",
      "partyBudgets": []
    }
  ],
  "politicalParties": [
    "Centerpartiet (C)",
    "Kristdemokraterna (KD)",
    "Liberalerna (L)",
    "Miljöpartiet (MP)",
    "Moderaterna (M)",
    "Socialdemokraterna (S)",
    "Sverigedemokraterna (SD)",
    "Vänsterpartiet (V)"
  ],
  "incomeCategories": [
    {
      "id": "tax_income",
      "name": "Statens skatteinkomster",
      "description": "Inkomster från skatter och avgifter",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 1344.97,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": 1402.15,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    },
    {
      "id": "activity_income",
      "name": "Inkomster av statens verksamhet",
      "description": "Inkomster från statliga verksamheter",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 47.89,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": 47.84,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    },
    {
      "id": "property_sales",
      "name": "Inkomster av försåld egendom",
      "description": "Inkomster från försäljning av statlig egendom",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 5.0,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": 5.0,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    },
    {
      "id": "loan_repayments",
      "name": "Återbetalning av lån",
      "description": "Återbetalningar av utgivna lån",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 0.61,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": 0.56,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    },
    {
      "id": "calculated_income",
      "name": "Kalkylmässiga inkomster",
      "description": "Beräknade inkomster och justeringar",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 22.16,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": 23.92,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    },
    {
      "id": "eu_grants",
      "name": "Bidrag m.m. från EU",
      "description": "Bidrag och stöd från Europeiska unionen",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 48.67,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": 48.77,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    },
    {
      "id": "tax_system_adjustments",
      "name": "Avräkningar m.m. i anslutning till skattesystemet",
      "description": "Justeringar och avräkningar i skattesystemet",
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": -145.05,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        },
        {
          "partyName": "Moderaterna (M)",
          "partyCode": "M",
          "budgetAmount": -154.37,
          "sourceUrl": "https://www.regeringen.se/contentassets/bfe4593f9b0d462f834bc8bbd052a921/budgetpropositionen-for-2025-hela-dokumentet-prop.2024251.pdf"
        }
      ]
    }
  ]
}
"""
}
