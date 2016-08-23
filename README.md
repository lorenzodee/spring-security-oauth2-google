# Spring Security OAuth2 with Google

This is a sample Spring MVC web application which secured by Spring Security. Instead of using form-based security, it is secured by Spring Security OAuth2 and the OAuth2 provider is Google.

The `google-oauth2.properties` (in `src/main/resources`) contains the details of the Google application which it uses to authenticate details. Change the values of the following attributes to the values for your application:

- `oauth2.clientId`
- `oauth2.clientSecret`

While the `pom.xml` file includes Sprint Boot dependencies, they're only used for dependency management (i.e. using the versions indicated in other POM files). But the web application itself does not use any of the Spring Boot auto-configuration features.

## To register a Google App perform the following steps

* Go to https://console.developers.google.com and login with your Google account (this will be the developer account and the email of this account will be published when someone tries to authenticate with the Google application)
* If you don't have a project create a new one and then click into the project.
* In the menu on the left side select "APIs & auth" --> "Credentials" --> "Create a new client ID"
* In the popup select the following
	- Application Type = Web Application
	- Authorized Javascript Origins = <YOUR DOMAIN>, 
	- Authorized Redirect URI = <THE CALL BACK HANDLER>, the URI for our application is /googleLogin so for local testing you should enter `http://localhost:8080/`*context-path*`/`*callback-path*
	- Copy the client ID and client Secret and update the properties file
	- Make sure you update the mandatory values on the "APIs & auth" --> "Consent screen" page as the application will not work without it.

When you have a the Google App configured, the Spring application is running, and you navigate to `http://localhost:8080/`*context-path*. It will show a welcome page with a link to a secured page. When you click on this link, you'll be asked to authenticate (if not yet authenticated). This will redirect you to a Google login page. Upon login it will ask you to authorize your application for access to your account to get email and profile data. On successful login it will render the secured HTML page which means the authentication was successful. It will also show some user information (e.g. ID, user name, and email).

## Technical Details

Please refer to [my blog post](http://lorenzo-dee.blogspot.com/2016/08/spring-security-oauth2-with-google.html).
