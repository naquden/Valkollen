package se.atte.partier.data

import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import partier.composeapp.generated.resources.Res

object JsonFileLoader {
    private val json = Json { ignoreUnknownKeys = true }
    
    suspend fun loadBudgetData(): BudgetData {
        val jsonBytes = Res.readBytes("files/budget_data.json")
        val jsonContent = jsonBytes.decodeToString()
        return json.decodeFromString<BudgetData>(jsonContent)
    }
}