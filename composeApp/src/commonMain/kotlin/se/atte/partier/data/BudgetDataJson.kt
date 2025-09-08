package se.atte.partier.data

object BudgetDataJson {
    const val JSON_CONTENT = """
{
  "year": 2024,
  "totalBudget": 1331.1,
  "budgetCategories": [
    {
      "id": "municipal_grants",
      "name": "Allmänna bidrag till kommuner",
      "description": "Finansierar välfärdstjänster som skola, sjukvård och social omsorg",
      "percentage": 13.1,
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 174.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        }
      ]
    },
    {
      "id": "sickness_benefits",
      "name": "Ekonomisk trygghet vid sjukdom och funktionsnedsättning",
      "description": "Sjukförsäkring, ersättningar och stöd till personer med funktionsnedsättning",
      "percentage": 8.8,
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 116.9,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        }
      ]
    },
    {
      "id": "healthcare",
      "name": "Hälsovård, sjukvård och social omsorg",
      "description": "Allmän hälso- och sjukvård samt social omsorg",
      "percentage": 8.3,
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 110.3,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        }
      ]
    },
    {
      "id": "family_support",
      "name": "Ekonomisk trygghet för familjer och barn",
      "description": "Barnbidrag, föräldrapenning och andra familjestöd",
      "percentage": 8.0,
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 106.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        }
      ]
    },
    {
      "id": "labor_market",
      "name": "Arbetsmarknad och arbetsliv",
      "description": "Arbetsmarknadspolitik, arbetsförmedling och arbetslivsutveckling",
      "percentage": 6.9,
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 92.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        }
      ]
    },
    {
      "id": "education",
      "name": "Utbildning och universitetsforskning",
      "description": "Grundskola, gymnasium, universitet och forskning",
      "percentage": 7.5,
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 99.5,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        }
      ]
    },
    {
      "id": "migration",
      "name": "Migration",
      "description": "Migrationsverket och asylprocessen",
      "percentage": 1.0,
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 13.8,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        }
      ]
    },
    {
      "id": "defense",
      "name": "Försvar och samhällets krisberedskap",
      "description": "Försvarsmakt, civilförsvar och säkerhetspolitik",
      "percentage": 9.5,
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 126.1,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/budgetpropositionen-for-2024-hela-dokumentet-prop.2023241.pdf"
        }
      ]
    },
    {
      "id": "justice",
      "name": "Rättsväsendet",
      "description": "Domstolar, åklagare och kriminalvård",
      "percentage": 5.7,
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
      "percentage": 6.2,
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
      "percentage": 1.4,
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
      "percentage": 1.2,
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
      "percentage": 0.2,
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
      "percentage": 1.8,
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
      "percentage": 1.0,
      "partyBudgets": []
    },
    {
      "id": "housing",
      "name": "Bostadsförsörjning och byggande",
      "description": "Bostadspolitik, hyresreglering och byggande",
      "percentage": 0.8,
      "partyBudgets": []
    },
    {
      "id": "research",
      "name": "Forskning och utveckling",
      "description": "Vetenskaplig forskning och teknisk utveckling",
      "percentage": 0.7,
      "partyBudgets": []
    },
    {
      "id": "employment",
      "name": "Arbetsmarknads- och utbildningsministeriet",
      "description": "Arbetsmarknadspolitik och utbildningsministeriet",
      "percentage": 0.6,
      "partyBudgets": []
    },
    {
      "id": "finance",
      "name": "Finansförvaltning",
      "description": "Skatter, tullar och ekonomisk förvaltning",
      "percentage": 0.5,
      "partyBudgets": []
    },
    {
      "id": "government",
      "name": "Statsrådsberedningen",
      "description": "Regeringskansliet och statsrådsberedningen",
      "percentage": 0.4,
      "partyBudgets": []
    },
    {
      "id": "parliament",
      "name": "Riksdagen",
      "description": "Riksdagens verksamhet och administration",
      "percentage": 0.3,
      "partyBudgets": []
    },
    {
      "id": "courts",
      "name": "Domstolsväsendet",
      "description": "Högre domstolar och specialdomstolar",
      "percentage": 0.2,
      "partyBudgets": []
    },
    {
      "id": "ombudsman",
      "name": "Justitieombudsmannen",
      "description": "Justitieombudsmannen och JO",
      "percentage": 0.1,
      "partyBudgets": []
    },
    {
      "id": "audit",
      "name": "Riksrevisionen",
      "description": "Riksrevisionen och statskontroll",
      "percentage": 0.1,
      "partyBudgets": []
    },
    {
      "id": "elections",
      "name": "Valmyndigheten",
      "description": "Valmyndigheten och valadministration",
      "percentage": 0.1,
      "partyBudgets": []
    },
    {
      "id": "data_protection",
      "name": "Integritetsskyddsmyndigheten",
      "description": "Integritetsskyddsmyndigheten och dataskydd",
      "percentage": 0.1,
      "partyBudgets": []
    },
    {
      "id": "competition",
      "name": "Konkurrensverket",
      "description": "Konkurrensverket och konkurrenspolitik",
      "percentage": 0.1,
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
  ]
}
"""
}
