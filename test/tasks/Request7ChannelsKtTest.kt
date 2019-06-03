package tasks

import contributors.MockGithubService
import contributors.concurrentProgressResults
import contributors.testRequestData
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class Request7ChannelsKtTest {
    @Test
    fun testChannels() = runBlocking {
        val startTime = System.currentTimeMillis()
        var index = 0
        loadContributorsChannels(MockGithubService, testRequestData) {
                users, _ ->
            val expected = concurrentProgressResults[index++]
            val time = System.currentTimeMillis() - startTime
            /*
            // TODO: uncomment this assertion
            Assert.assertEquals("Expected intermediate results after virtual ${expected.timeFromStart} ms:",
                expected.timeFromStart, virtualTime)
            */
            Assert.assertEquals("Wrong progress result after $time:", expected.users, users)
        }
    }
}