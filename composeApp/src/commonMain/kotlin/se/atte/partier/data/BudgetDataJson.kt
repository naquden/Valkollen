package se.atte.partier.data

object BudgetDataJson {
    const val JSON_CONTENT = """
{
  "year": 2025,
  "totalBudget": 1428.0,
  "budgetCategories": [
    {
      "id": "municipal_grants",
      "name": "Allmänna bidrag till kommuner",
      "description": "Finansierar välfärdstjänster som skola, sjukvård och social omsorg",
      "percentage": 13.3,
      "partyBudgets": []
    },
    {
      "id": "sickness_benefits",
      "name": "Ekonomisk trygghet vid sjukdom och funktionsnedsättning",
      "description": "Sjukförsäkring, ersättningar och stöd till personer med funktionsnedsättning",
      "percentage": 9.9,
      "partyBudgets": []
    },
    {
      "id": "healthcare",
      "name": "Hälsovård, sjukvård och social omsorg",
      "description": "Allmän hälso- och sjukvård samt social omsorg",
      "percentage": 8.5,
      "partyBudgets": []
    },
    {
      "id": "family_support",
      "name": "Ekonomisk trygghet för familjer och barn",
      "description": "Barnbidrag, föräldrapenning och andra familjestöd",
      "percentage": 8.4,
      "partyBudgets": []
    },
    {
      "id": "labor_market",
      "name": "Arbetsmarknad och arbetsliv",
      "description": "Arbetsmarknadspolitik, arbetsförmedling och arbetslivsutveckling",
      "percentage": 7.3,
      "partyBudgets": []
    },
    {
      "id": "education",
      "name": "Utbildning och universitetsforskning",
      "description": "Grundskola, gymnasium, universitet och forskning",
      "percentage": 7.0,
      "partyBudgets": []
    },
    {
      "id": "migration",
      "name": "Migration",
      "description": "Migrationsverket och asylprocessen",
      "percentage": 3.2,
      "partyBudgets": [
        {
          "partyName": "Sverigedemokraterna (SD)",
          "partyCode": "SD",
          "budgetAmount": 13.8,
          "sourceUrl": "https://www.regeringen.se/contentassets/e1afccd2ec7e42f6af3b651091df139c/utgiftsomrade-8-migration.pdf"
        }
      ]
    },
    {
      "id": "defense",
      "name": "Försvar och samhällets krisberedskap",
      "description": "Försvarsmakt, civilförsvar och säkerhetspolitik",
      "percentage": 5.2,
      "partyBudgets": []
    },
    {
      "id": "justice",
      "name": "Rättsväsendet",
      "description": "Domstolar, åklagare och kriminalvård",
      "percentage": 2.8,
      "partyBudgets": []
    },
    {
      "id": "transport",
      "name": "Kommunikationer",
      "description": "Vägar, järnvägar och kollektivtrafik",
      "percentage": 2.5,
      "partyBudgets": []
    },
    {
      "id": "environment",
      "name": "Miljö och klimat",
      "description": "Miljöskydd, klimatåtgärder och naturvård",
      "percentage": 2.1,
      "partyBudgets": []
    },
    {
      "id": "culture",
      "name": "Kultur, medier, trossamfund och fritid",
      "description": "Kulturstöd, public service och idrott",
      "percentage": 1.8,
      "partyBudgets": []
    },
    {
      "id": "foreign_affairs",
      "name": "Utrikesförvaltning",
      "description": "Diplomati, utvecklingssamarbete och internationella organisationer",
      "percentage": 1.5,
      "partyBudgets": []
    },
    {
      "id": "agriculture",
      "name": "Jordbruk, skogsbruk och fiske",
      "description": "Lantbruksstöd, skogsbruk och fiskeripolitik",
      "percentage": 1.2,
      "partyBudgets": []
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
