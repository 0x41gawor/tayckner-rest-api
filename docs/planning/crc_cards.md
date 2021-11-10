# CRC Cards

> Class responsibility-collaboration cards. [Wikipedia](https://en.wikipedia.org/wiki/Class-responsibility-collaboration_card).
>
> From [Description in text](text_description.md) model classes were identified. This document will define responsibilities and collaboration classes for each model class.

## Day-Tracker

|         Class         |     *Activity*     |
| :-------------------: | :----------------: |
| **Responsibilities:** | **Collaborators:** |
|    Knows its Name     |                    |
|  Knows its Category   |      Category      |
| Knows its Start time  |                    |
|  Knows its End time   |                    |
|  Knows its Duration   |                    |
| Know its Breaks time  |                    |

|        Class:         |     *Category*     |
| :-------------------: | :----------------: |
| **Responsibilities:** | **Collaborators:** |
|    Knows its Name     |                    |
| Knows its Description |                    |
|    Knows its Color    |       Color        |
|    Knows its User     |        User        |

## Day-Planner

|        Class:         |     *Schedule*     |
| :-------------------: | :----------------: |
| **Responsibilities:** | **Collaborators:** |
|    Knows its Name     |                    |
| Knows its Start time  |                    |
|  Knows its End time   |                    |
|  Knows its Duration   |                    |
|    Knows its User     |                    |

## Habit-Tracker

|        Class:         |    *HabitEvent*    |
| :-------------------: | :----------------: |
| **Responsibilities:** | **Collaborators:** |
|    Knows its Habit    |       Habit        |
|    Knows its Date     |                    |
|   Knows its Comment   |                    |
|    Knows its Value    |                    |

|        Class:         |      *Habit*       |
| :-------------------: | :----------------: |
| **Responsibilities:** | **Collaborators:** |
|    Knows its Name     |                    |
|    Knows its Color    |       Color        |
|    Knows its User     |        User        |

## Common

|            Class:            |      *Color*       |
| :--------------------------: | :----------------: |
|    **Responsibilities:**     | **Collaborators:** |
|        Knows its Code        |                    |
| Can valid if Code is correct |                    |

|        Class:         |       *User*       |
| :-------------------: | :----------------: |
| **Responsibilities:** | **Collaborators:** |
| Knows its First name  |                    |
|  Knows its Last name  |                    |
|  Knows its Username   |                    |
|   Knows its E-mail    |                    |
|  Knows its password   |                    |
|  Can CRUD Activities  |      Activity      |
|  Can CRUD Categories  |      Category      |
|  Can CRUD Schedules   |      Schedule      |
| Can CRUD HabitEvents  |     HabitEvent     |
|    Can CRUD Habits    |       Habit        |

> CRUD - is an acronym for **C**reate, **R**ead, **U**pdate, **D**elete. 
>
> In computer programming these are the four basic operations of persistent storage.
>
> Instead of writing 4 rows for each class from set {Activity, Category, Schedule, HabitEvent, Habit}, I just use this well known shortcut.
>
> ! Note that words like {Create, Read, Update} weren't used in [Description in text](text_description.md). But each one of them has its counterpart:
>
> "User can add ..." --> "User can create"
>
> "User can view list of ..." --> "User can read(used in Computer Science context) values form class ..."
>
> "User can modify" --> "User can update"

