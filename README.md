## PROJECT: todo

>### Description:
TODO list
___________________________After successful authorization, a list of tasks is available to you_____________________________________________
>### Technologies used:
  - Spring boot;
  - Thymeleaf;
  - Bootstrap;
  - Hibernate.
________________________________________________________________________
>### Environment requirements:
  - Java 17;
  - Maven 3.8;
  - PostgreSQL 14.
________________________________________________________________________
>### How to start:
- Soon...
________________________________________________________________________
>### Application Interaction:
- ##### The application opens at http://localhost:8080/index
![Main.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FMain.png)



- ##### The authorization window will appear when you select any menu item
![Auth.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FAuth.png)
  

- ##### A new user registration form is also available
![Reg_new_user.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FReg_new_user.png)


- ##### Login must be unique, otherwise authorization will fail
![Reg_success.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FReg_success.png)
![Reg_fail.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FReg_fail.png)


- ##### After successful authorization, a list of tasks is available to you
![All_tasks.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FAll_tasks.png)
  - **_The task is bound to the user. In addition, the task execution time depends on the user's time zone._**
  - **_The time entry in the database is configured by default in the time zone "UTC"._**
  - **_The priority and category fields are set separately at the level of queries to the database._**



- ##### The editing page allows you to change any fields of the task
![Edit_menu.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FEdit_menu.png)
![Edit_menu_fields.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FEdit_menu_fields.png)


- ##### In the navigation menu, you can sort completed and failed tasks
![Done_list.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FDone_list.png)
![Undone_list.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FUndone_list.png)

- ##### Additionally, a time zone converter has been implemented.
![TZ_service.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FTZ_service.png)
![TZ_list.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FTZ_list.png)
  - **_Display of all time zones._**

![TZ_local_time.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FTZ_local_time.png)
  - **_Display of local and absolute time._**

![TZ_two_time.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FTZ_two_time.png)
![TZ_two_time_1.png](src%2Fmain%2Fresources%2FReadmeScrShots%2FTZ_two_time_1.png)
- **_Display selected, local and absolute time._**

________________________________________________________________________

Author: <a href="https://github.com/Futsey" title="Andrew Petrushin">Andrew Petrushin</a>