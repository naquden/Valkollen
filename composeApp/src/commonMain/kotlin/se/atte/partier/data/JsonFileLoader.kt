package se.atte.partier.data

import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

object JsonFileLoader {
    private val json = Json { ignoreUnknownKeys = true }
    
    fun loadBudgetData(): BudgetData {
        return json.decodeFromString<BudgetData>(BudgetDataJson.JSON_CONTENT)
    }
}