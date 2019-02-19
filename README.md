# Pre-work - Todo


The product Tweeter allows users to post short messages limited to 50 characters each.
Sometimes, users get excited and write messages longer than 50 characters.
Instead of rejecting these messages, we would like to add a new feature that will split the message into parts and send multiple messages on the user's behalf, all of them meeting the 50 character requirement.

Submitted by: Hoang Duc Huu

Time spent: about  over 5 hours spent in total (including study time)

## User Stories and Requirements Check list

The following **required** functionality is completed:
### 1. ​Create an Android application that serves the Tweeter interface​. It will support
* [x] Allow the user to input and send messages.
* [x] Display the user's messages.
* [x] If a user's input is less than or equal to 50 characters, post it as is.
* [x] If a user's input is greater than 50 characters, split it into chunks that each is less than or equal to 50 characters and post each chunk as a separate message.
* [x] Messages will only be split on whitespace. If the message contains a span of non-whitespace characters longer than 50 characters, display an error.
* [x] Split messages will have a "part indicator" appended to the beginning of each section. In the example above, the message was split into two chunks, so the part indicators read "1/2" and "2/2". Be aware that these count toward the character limit.

[x] 2   The functionality that splits messages should be a standalone function​.
* [x] Given the above example, its function call would look like:
splitMessage("I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself.")
and it would return
["1/2 I can't believe Tweeter now supports chunking", "2/2 my messages, so I don't have to do it myself."]

[x] 3   The business logic should be unit tested. We have to know it works?

[x] ​The submission should be a git repository​. In the project directory, `git log` should 
show your commits.
[x] 5. Bonus points for any additional polish and sophistication you add to the experience.
* [x] Activity and fragment life cycle, handling configuration changes

`update`



## Languages, libraries... and tools used

* [Kotlin](https://kotlinlang.org/)
* Android Support Libraries
* [RxJava2](https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0)
* [Koin 2 ](https://insert-koin.io/): to appy Dependency Injection
*  Using Clean Architecture Way: Follow [Fernando Cejas Blogs](https://github.com/android10): 

## Note:
* this version just use only Local database 

## Issue and still not complete:
* Unitest all layers
* Still not observe ROOM database changes from DataSource-layer (remove, add) in UI-layer



## License


    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.