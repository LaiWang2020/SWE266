This is the simple system project for build phase assignment in MSWE 266P 

Author: Chupeng Zhang, Yu Sun, Lai Wang, Xinyi Hu

# **Project Architecture:**

Front End:

1. JSP
2. Bootstrap
3. CSS

Back End:

1. Spring MVC
2. Tomcat 9

Data Base:
1. Mysql Database

# **Build/Test Instruction:**


We developed a web app, using Tomcat version 9. To load Tomcat version 9, you need to firstly go to the [official website](http://tomcat.apache.org/) to download Tomcat. 

Here we are using version 9.0.45. You can feel free to download 32-bit or 64-bit.

![image.png](https://i.loli.net/2021/05/11/QOwBME7CoNjsfKr.png)

Then unzip the package into any directory you like. 

Next, open your intelliJ and open existing repository. Edit configurations to add Tomcat here.

![image.png](https://i.loli.net/2021/05/11/8nBcDdCgrbaUIlm.png)

Add new configurations, and press Tomcat Server > local

![image.png](https://i.loli.net/2021/05/11/W3sRVbmk2aSuhKT.png)

In configure button, choose your unzipped Tomcat folder directory. Add HTTP port as 8080, JMX port as 1099.

![image.png](https://i.loli.net/2021/05/11/5wiADpvufVQ7GZT.png)

Press deployment button and add artifact here. 

![image.png](https://i.loli.net/2021/05/11/4d7gtLc3vfsryqX.png)

## Database 

To make the sql and database work, firstly you need to log in your local database at first and execute the sql file in the directory: src\main\java\edu\swe266\sql. 

![image.png](https://i.loli.net/2021/05/11/SFDswimVzv7u4nQ.png)

Then you need to change the root password in jdbc.properties. Directory: src\main\resources

![image.png](https://i.loli.net/2021/05/11/TIltrkSEUaoD8jf.png)

Then your database is done!