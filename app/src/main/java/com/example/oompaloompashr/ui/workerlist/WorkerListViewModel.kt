package com.example.oompaloompashr.ui.workerlists.workerlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.oompaloompashr.model.Result
import com.example.oompaloompashr.data.WorkersListRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private const val LAST_QUERY_SCROLLED: String = "last_query_scrolled"
private const val LAST_SEARCH_QUERY: String = "last_search_query"
private const val DEFAULT_QUERY = ""

@OptIn(ExperimentalCoroutinesApi::class)
class WorkerListViewModel (
    private val savedStateHandle: SavedStateHandle,
    val workersListRepository: WorkersListRepository
) : ViewModel() {

    private val ITEMS_PER_PAGE: Int = 20

    val state: StateFlow<UiState>
    val pagingDataFlow: Flow<PagingData<UiModel>>
    val accept: (UiAction) -> Unit


init {
    val initialQuery: String = savedStateHandle.get(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
    val lastQueryScrolled: String = savedStateHandle.get(LAST_QUERY_SCROLLED) ?: DEFAULT_QUERY
    val actionStateFlow = MutableSharedFlow<UiAction>()
    val searches = actionStateFlow
        .filterIsInstance<UiAction.Search>()
        .distinctUntilChanged()
        .onStart { emit(UiAction.Search(query = initialQuery)) }
    val queriesScrolled = actionStateFlow
        .filterIsInstance<UiAction.Scroll>()
        .distinctUntilChanged()
        // This is shared to keep the flow "hot" while caching the last query scrolled,
        // otherwise each flatMapLatest invocation would lose the last query scrolled,
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            replay = 1
        )
        /*.onStart { emit(UiAction.Scroll(currentQuery = lastQueryScrolled)) }*/

    pagingDataFlow = searches
        .flatMapLatest { searchRepo(queryString = it.query) }
        .cachedIn(viewModelScope)

    state = combine(
        searches,
        queriesScrolled,
        ::Pair
    ).map { (search, scroll) ->
        UiState(
            query = search.query,
            lastQueryScrolled = scroll.currentQuery,
            // If the search query matches the scroll query, the user has scrolled
            hasNotScrolledForCurrentSearch = search.query != scroll.currentQuery
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
            initialValue = UiState()
        )

    accept = { action ->
        viewModelScope.launch { actionStateFlow.emit(action) }
    }
}


    override fun onCleared() {
        savedStateHandle[LAST_SEARCH_QUERY] = state.value.query
        savedStateHandle[LAST_QUERY_SCROLLED] = state.value.lastQueryScrolled
        super.onCleared()
    }


    private fun searchRepo(queryString: String): Flow<PagingData<UiModel>> =
        workersListRepository.getSearchResultStream(queryString)
            .map { pagingData -> pagingData.map { UiModel.RepoItem(it) } }

    }


sealed class UiAction {
    data class Search(val query: String) : UiAction()
    data class Scroll(val currentQuery: String) : UiAction()
}

data class UiState(
    val query: String = DEFAULT_QUERY,
    val lastQueryScrolled: String = DEFAULT_QUERY,
    val hasNotScrolledForCurrentSearch: Boolean = false
)

sealed class UiModel {
    data class RepoItem(val result: Result) : UiModel()
    data class SeparatorItem(val description: String) : UiModel()
}
