package com.rubio.movstore.domain.usecases

import com.rubio.movstore.data.datasource.remote.MoviesApi
import com.rubio.movstore.data.models.Movie
import com.rubio.movstore.data.repository.MoviesRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import retrofit2.Call

internal class GetCatalogueTest {

    private var getCatalogue: GetMoviesUseCase? = null
    private lateinit var repositoryMockK: MoviesRepository
    private lateinit var callMockitoRetrofit: Call<*>


    private lateinit var client: okhttp3.OkHttpClient
    private lateinit var apiService: MoviesApi
    //private  var repositoryMockito: CatalogueRepository? = null

    @BeforeEach
    fun setUp() {
        repositoryMockK = mockk()

        callMockitoRetrofit = Mockito.mock(
            Call::class.java
        )
        // repositoryMockito = Mockito.mock(CatalogueRepository::class.java)
        getCatalogue = GetMoviesUseCase(repositoryMockK)


        // Setup a new mock for each test case
        client = mockk(relaxed = true)
        apiService = mockk()
    }

    @AfterEach
    fun tearDown() {
        // Clean method - End execute.
        getCatalogue = null
    }

    @Test
    fun getCatalogueNotNullTest() {
        assertNotNull(getCatalogue, "GetCatalogue no debe ser null")
    }

    //@Disabled("Hasta que se haga la implementación")
    @Test
    fun insertInDB() {
        val movies: ArrayList<Movie>? = null
        movies?.add(
            Movie(
                0,
                false,
                "gbhdfh",
                1,
                "en", "sdgsd",
                "sgbsdfbdfb",
                2.3,
                "xbfbnf",
                "0202/202",
                "sfdbvsfdb",
                false,
                2.36f,
                25
            )
        )
        coEvery { getCatalogue?.insertInDB(movies) }.returns(Unit)
    }

    @Disabled("Hasta hacer la implementación")
    @Test
    fun getListCatalogueTest() {
        every { getCatalogue?.invoke("algo", response = {}) } answers {

        }
    }

    @Test
    fun getAllMoviesLocalDBMockK() {
        val expected = listOf<Movie>()
        var response1: List<Movie>? = null
        coEvery {
            getCatalogue?.getAllMoviesLocalDB {
                response1 = it
            }
        } returns Unit
        assertEquals(expected, response1)
    }


    @Test
    fun toFahrenheitTest() {
        assertEquals(55.4f, getCatalogue!!.toFahrenheit(13F), 0.01f)
    }
}