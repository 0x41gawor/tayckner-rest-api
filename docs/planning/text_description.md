# Text description

> Text description is an informal (not using any notation) document that describes what and with what kind of objects user can do. This description should contain the smallest business knowledge as possible. It describes the system, but without any business context.
>
>  Later, based on this document the CRC cards will be created. That's why every noun, that prospers to be an object, and every verb that describes what can be done with objects is marked with bold text. 
>
> It is very likely that this document will be change multiple times.

As for now application has 3 modules:

- Day-Tracker
- Day-Planner
- Habit-Tracker

And they can be described separately.

## Day-Tracker

This module lest user track his **activities** done during the day. An activity contains  information such as: name, **category**, start time, end time, duration, breaks. User is able to define his categories. Category is described with name, description and **color**. On the main view **user can see his activities done this day**, from this view he is also able to **add new activity**. 

At the start of the day - before any activity is added user can only **add special activity which is "Wake up time"**. Duration of this activity is equal to zero. Same as with the **"Bed time"** - which user adds as a time he put himself to bed. 

**User can modify the activities** especially **add small breaks** to them. 

There are two methods for entering the activity time gap. Both require to fill in the start time, but in one user enters end time and in second duration time. In both cases the second variable is computed automatically. But remember - user is not forced to enter both end time or duration within creating the activity. He just can't add another if neither of that is done.

**User can** also **delay activity** for some small amount of time with additional button, instead of modifying the start time.

**User is able to delete an activity.**

After every day/week/month/year **user can see the Report** of how much time he spend on each category of activities. Especially the "gray zone" - the time not covered by any activities.

## Day-Planner

This module lets user plan his day. The unit of day plan here is called **Schedule**. Schedule contains information such as: name, start time, end time and duration. From the main view **user can see list** of schedule planned for current day. **User can add, modify and delete schedules**. 

We let user add schedules with the same start time as the end time.

Schedules are not bound in any way with activities form Day-Tracker module.

## Habit-Tracker

This module lets **user define his habits** and later add **habit events** (occurrences). Habit is described with name and color. Habit event contains information such as date of occurrence (with the accuracy of day), comment and value. **User can add habit events** and **see them** as a list or as a calendar view. **User can** also **modify and delete habit events.**

## Common for each module

**User** can be **registered** which gives him ability to **log-in** to the app and perform all actions defined above. User contains such information as: first name, last name, username, email address, and password.

