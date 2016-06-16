# Pre-work - *Todue*

**Todue** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item. In addition to that you can also have a task recur on a weekly basis and set a due date for tasks to be completed by.

Submitted by: **Jaicob Stewart**

Time spent: **13-16** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **successfully add and remove items** from the todo list
* [X] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [X] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [X] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [X] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [X] Add support for completion due dates for todo items (and display within listview item)
* [X] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [X] Add support for selecting the priority of each todo item (and display in listview item via increasing text size)
* [X] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [X] User can have tasks recur weekly
* [X] User sees urgency of tasks since the color of the item darkens as it approaches due date
* [X] Completing tasks is animated

## Video Walkthrough 

Here's a walkthrough of implemented user stories:


<img src='http://i.imgur.com/GWESFAp.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

* Terminology: This is the first time i've ever done anything in android and a lot of the terminology used is a little quirky (like activity and fragment) so I spent a good amount of time in the docs learning the lingo. As I went I was able to start relating a lot of the concepts used in android development to other frameworks I've worked in and it started getting more intuitive.
* Fragmentation: There are many different api versions, android versions, android studio versions etc. This made it a little difficult to find reliable and up to date references. To overcome this I looked at a broad array of sources to try and draw a consensus and also fiddled around a good bit. 
* Design: I used a mix of the text-based and visual design tools in android studio. It was a little tricky getting things to look just the way you want, but after a decent amount of experimentation with different layout styles, attributes, etc. I came up with something okay. Will be fun to learn more about front end android work.


## License

    Copyright [2016] [Jaicob Stewart]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
