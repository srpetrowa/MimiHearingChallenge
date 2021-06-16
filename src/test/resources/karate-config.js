function fn() {
    var defaultHeaders = {
        'Content-Type': 'application/vnd.api+json',
        Accept: 'application/vnd.api+json'
    };
    /*
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

     */


    /*
    if (!!wso2CertFile) {
        // use provided client certificate for authentication against wso2
        karate.configure('ssl', { keyStore: wso2CertFile, keyStorePassword: '', keyStoreType: 'pkcs12' })
    } else {
        // No cert file given --> Assumption: running against locally deployed system.
        // Use local test token for authentication.
        defaultHeaders.Authorization = 'Bearer ' + karate.readAsString('classpath:token/authorizationToken.txt');
    }*/

    karate.configure('headers', defaultHeaders);

    return {
        // ./gradlew e2e -Dkarate.baseUrl=https://pokeapi.co/api/v2/
        /*baseUrl: java.lang.System.getProperty("karate.baseUrl") || 'http://localhost:8080', // set base url explicitly or use localhost*/
        baseUrl: java.lang.System.getProperty("karate.baseUrl") || 'https://trello.com/1', // set base url explicitly or use localhost
        trelloToken: java.lang.System.getProperty("trello.token") || '1421c7ed4c177b869fc74ebe6dfbd8cb7cbe33ac62b6339115c5d18f59aabc1e',
        trelloKey: java.lang.System.getProperty("trello.key") || '18370d76b2825815b175c05e9f690c2c'
    };
}
