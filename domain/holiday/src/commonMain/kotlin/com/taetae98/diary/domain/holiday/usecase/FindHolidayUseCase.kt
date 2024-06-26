package com.taetae98.diary.domain.holiday.usecase

import com.taetae98.diary.core.model.holiday.Holiday
import com.taetae98.diary.domain.core.FlowUseCase
import com.taetae98.diary.domain.holiday.repository.HolidayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Month
import org.koin.core.annotation.Factory

@Factory
public class FindHolidayUseCase internal constructor(
    private val repository: HolidayRepository,
) : FlowUseCase<List<Pair<Int, Month>>, List<Holiday>>() {
    override fun execute(param: List<Pair<Int, Month>>): Flow<Result<List<Holiday>>> {
        return repository.findHoliday(param)
            .map { Result.success(it) }
    }
}
