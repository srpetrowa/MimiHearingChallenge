# tooling-karate-template

Karate Boilerplate for API testing. This template uses Black Belt Library in order to create reports via Cucumber. 
This Boilerplate is set as a Kotlin project. 

## Setup
There are 3 main files that need to be configurated for your special needs: 

### Setup build.gradle.kts

If you do not want to use the Black Belt Library or you want to change its version, you need to edit the added dependancy: 
```
dependencies {
    testImplementation("....")
}
```
You can set the path to your test resources here: 
```
sourceSets {
    test {
        resources {
            srcDirs("src/test/kotlin")
        }
    }
}
```
And you can set the system property for API url - `baseURL`, test resources and any certificates you may need like so: 
```
systemProperty("karate.baseUrl", "https://pokeapi.co/api/v2/")
systemProperty("karate.certificateFile", System.getenv("SOME_CERT_FILE"))
systemProperty("karate.config.dir", "src/test/resources")
```

### Setup karate-config.js

The karate config file can be used to set diffetent certificates either staticly or by calling the appropriate system property. 
Different environements could be configurated with specific settings, test data and so on. 
If an autorisation token is needed one can be set here as well. 
```
function fn() {
    var certificateFile = java.lang.System.getProperty("karate.certificateFile");

    var defaultHeaders = {
        'Content-Type': 'application/vnd.api+json',
        Accept: 'application/vnd.api+json'
    };
  
    var env = karate.env || 'local'; // get java system property 'karate.env'
    karate.log("Environment: " + env)

    if (env == 'review') {
    // configure review environment
    }

    if (env == 'dev' || env == 'int') {
        // configure dev or int environment
    }

    if (env == 'qa') {
        // configure QA environment
    }

    

    if (!!certificateFile) {
        // use provided client certificate for authentication 
        karate.configure('ssl', { keyStore: certificateFile, keyStorePassword: '', keyStoreType: '' })
    } else {
        // No cert file given ->  running against locally deployed system.
        // Use local test token for authentication.
        defaultHeaders.Authorization = 'Bearer ' + karate.readAsString('classpath:token/authorizationToken.txt');
    }

    karate.configure('headers', defaultHeaders);

    return {
          baseUrl: java.lang.System.getProperty("karate.baseUrl") || 'http://localhost:8080', // set base url explicitly or use localhost
    };
}```

## Running the tests

```

```
