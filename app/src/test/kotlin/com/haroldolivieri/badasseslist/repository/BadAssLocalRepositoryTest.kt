package com.haroldolivieri.badasseslist.repository

import callPrivateMethod
import diffItemsFile
import equalItemsFile
import nullDataOnItemsFile
import org.hamcrest.Matchers.hasSize
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner
import java.io.FileInputStream

@RunWith(PowerMockRunner::class)
class BadAssLocalRepositoryTest {

    @Test
    fun readJsonFromExistingFile() {
        val file = diffItemsFile(javaClass)
        val repository = BadAssLocalRepository(FileInputStream(file))

        Assert.assertEquals(callPrivateMethod(repository, "readDataLocally", emptyArray()),
                badAssesTwoDiff)
    }

    @Test
    fun readJsonWithNullData() {
        val file = nullDataOnItemsFile(javaClass)
        val repository = BadAssLocalRepository(FileInputStream(file))

        Assert.assertEquals(callPrivateMethod(repository, "readDataLocally", emptyArray()),
                badAssesWithNullData)
    }

    @Test(expected = NullPointerException::class)
    fun errorReadingNullId() {
        val repository = BadAssLocalRepository(FileInputStream(nullDataOnItemsFile(javaClass)))

        assertThat(callPrivateMethod(repository, "toSet", arrayOf(badAssesWithNullData)) as Set<*>,
                hasSize(2))
    }

    @Test
    fun getListFromJsonWithTwoDiffItems() {
        val repository = BadAssLocalRepository(FileInputStream(diffItemsFile(javaClass)))

        assertThat(callPrivateMethod(repository, "toSet", arrayOf(badAssesTwoDiff)) as Set<*>,
                hasSize(2))
    }

    @Test
    fun getListFromJsonWithTwoEqualsItems() {
        val repository = BadAssLocalRepository(FileInputStream(equalItemsFile(javaClass)))

        assertThat(callPrivateMethod(repository, "toSet", arrayOf(badAssesTwoEquals)) as Set<*>,
                hasSize(1))
    }

    @Test
    fun testFetchFromDiffItemsFile() {
        val repository = BadAssLocalRepository(FileInputStream(diffItemsFile(javaClass)))
        val testObserver = repository.fetch().toList().test()

        testObserver
                .assertValue { t -> t.size == 2 }
                .assertNoErrors()
    }

    @Test
    fun testFetchFromEqualsItemsFile() {
        val repository = BadAssLocalRepository(FileInputStream(equalItemsFile(javaClass)))
        val testObserver = repository.fetch().toList().test()

        testObserver
                .assertValue { t -> t.size == 1 }
                .assertNoErrors()
    }

    private val badAssesTwoDiff = "[\n" +
            "  {\n" +
            "    \"id\": \"97d16abc-1569-43e0-929b-cef05cd850fb\",\n" +
            "    \"name\": \"Steve Jobs\",\n" +
            "    \"image\": \"http://adsoftheworld.com/files/steve-jobs.jpg\",\n" +
            "    \"birthday\": \"1955-02-24T00:00:00Z\",\n" +
            "    \"bio\": \"Steven Paul Jobs was an American businessman, inventor, and industrial designer. He was the co-founder, chairman,and chief executive officer (CEO) of Apple Inc.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"599df0e4-183c-4bfb-91c5-881d4550cd3f\",\n" +
            "    \"name\": \"Craig Federighi\",\n" +
            "    \"image\": \"http://www.apple.com/pr/bios/images/federighi_hero20120727.png\",\n" +
            "    \"birthday\": \"1969-05-27T00:00:00Z\",\n" +
            "    \"bio\": \"Craig Federighi is Apple's senior vice president of Software Engineering. Federighi oversees the development of iOS, macOS and Apple's common operating system engineering teams.\"\n" +
            "  }\n" +
            "]"

    private val badAssesTwoEquals = "[\n" +
            "  {\n" +
            "    \"id\": \"97d16abc-1569-43e0-929b-cef05cd850fb\",\n" +
            "    \"name\": \"Steve Jobs\",\n" +
            "    \"image\": \"http://adsoftheworld.com/files/steve-jobs.jpg\",\n" +
            "    \"birthday\": \"1955-02-24T00:00:00Z\",\n" +
            "    \"bio\": \"Steven Paul Jobs was an American businessman, inventor, and industrial designer. He was the co-founder, chairman,and chief executive officer (CEO) of Apple Inc.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"97d16abc-1569-43e0-929b-cef05cd850fb\",\n" +
            "    \"name\": \"Craig Federighi\",\n" +
            "    \"image\": \"http://www.apple.com/pr/bios/images/federighi_hero20120727.png\",\n" +
            "    \"birthday\": \"1969-05-27T00:00:00Z\",\n" +
            "    \"bio\": \"Craig Federighi is Apple's senior vice president of Software Engineering. Federighi oversees the development of iOS, macOS and Apple's common operating system engineering teams.\"\n" +
            "  }\n" +
            "]"

    private val badAssesWithNullData = "[\n" +
            "  {\n" +
            "    \"id\": null,\n" +
            "    \"name\": null,\n" +
            "    \"image\": \"http://adsoftheworld.com/files/steve-jobs.jpg\",\n" +
            "    \"birthday\": \"1955-02-24T00:00:00Z\",\n" +
            "    \"bio\": \"Steven Paul Jobs was an American businessman, inventor, and industrial designer. He was the co-founder, chairman,and chief executive officer (CEO) of Apple Inc.\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"599df0e4-183c-4bfb-91c5-881d4550cd3f\",\n" +
            "    \"name\": \"Craig Federighi\",\n" +
            "    \"image\": null,\n" +
            "    \"birthday\": \"1969-05-27T00:00:00Z\",\n" +
            "    \"bio\": \"Craig Federighi is Apple's senior vice president of Software Engineering. Federighi oversees the development of iOS, macOS and Apple's common operating system engineering teams.\"\n" +
            "  }\n" +
            "]"


}

