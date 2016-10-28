package com.nullpointerbay.u2020k.model

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith




@RunWith(JUnitPlatform::class)
class JsonParsingTest : Spek({


    describe("A json") {

        val apiJson = """
    {
  "total_count": 40,
  "incomplete_results": false,
  "items": [
    {
      "id": 3081286,
      "name": "Tetris",
      "full_name": "dtrupenn/Tetris",
      "owner": {
        "login": "dtrupenn",
        "id": 872147,
        "avatar_url": "https://secure.gravatar.com/avatar/e7956084e75f239de85d3a31bc172ace?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-user-420.png",
        "followers_url": "https://someurl.com",
        "url": "https://api.github.com/users/dtrupenn",
        "html_url": "https://api.github.com/users/dtrupenn",
        "received_events_url": "https://api.github.com/users/dtrupenn/received_events",
        "type": "User"
      },
      "private": false,
      "html_url": "https://github.com/dtrupenn/Tetris",
      "description": "A C implementation of Tetris using Pennsim through LC4",
      "fork": false,
      "url": "https://api.github.com/repos/dtrupenn/Tetris",
      "created_at": "2012-01-01T00:31:50Z",
      "updated_at": "2013-01-05T17:58:47Z",
      "pushed_at": "2012-01-01T00:37:02Z",
      "homepage": "",
      "size": 524,
      "stargazers_count": 1,
      "watchers_count": 2,
      "language": "Assembly",
      "forks": 3,
      "open_issues_count": 0,
      "master_branch": "master",
      "default_branch": "master",
      "score": 10.309712
    }
  ]
}
    """

        val gson = Gson()
        val apiSummary = gson.fromJson<ApiSummary>(apiJson)


        it("should be parsed to valid objects") {

            assertEquals(40, apiSummary.totalCount)
            assertEquals(1, apiSummary.items.size)
            assertEquals(false, apiSummary.incompleteResults)


        }

        it("should parse items") {
            val item = apiSummary.items[0]

            assertEquals(3081286, item.id)
            assertEquals("Tetris", item.name)
            assertEquals("dtrupenn/Tetris", item.fullName)
            assertEquals(3, item.forks)
            assertEquals("https://github.com/dtrupenn/Tetris", item.htmlUrl)
            assertEquals("Assembly", item.language)
            assertEquals(1, item.stargazersCount)
            assertEquals(2, item.watchersCount)

        }

        it("should parse item's owner") {
            val owner = apiSummary.items[0].owner

            assertEquals(872147, owner.id)
            assertEquals("https://secure.gravatar.com/avatar/e7956084e75f239de85d3a31bc172ace?d=https://a248.e.akamai.net/assets.github.com%2Fimages%2Fgravatars%2Fgravatar-user-420.png", owner.avatarUrl)
            assertEquals("https://someurl.com", owner.followersUrl)
            assertEquals("https://api.github.com/users/dtrupenn", owner.htmlUrl)
            assertEquals("dtrupenn", owner.login)


        }



    }
})