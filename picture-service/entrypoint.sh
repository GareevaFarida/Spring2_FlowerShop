#!/usr/bin/env sh

if [ -z "$JDBC_DATABASE_URL" ]; then
    echo "[INFO] Extracting JDBC_DATABASE_URL from Heroku DATABASE_URL"

    # extract the protocol
    proto="`echo $DATABASE_URL | grep '://' | sed -e's,^\(.*://\).*,\1,g'`"
    # remove the protocol
    url=`echo $DATABASE_URL | sed -e s,$proto,,g`

    echo "[INFO] protocol: "$proto

    # extract the user and password (if any)
    userpass="`echo $url | grep @ | cut -d@ -f1`"
    pass=`echo $userpass | grep : | cut -d: -f2`
    if [ -n "$pass" ]; then
        user=`echo $userpass | grep : | cut -d: -f1`
    else
        user=$userpass
    fi

    echo "[INFO] password= "$pass
    echo "[INFO] user= "$user

    # extract the host -- updated
    hostport=`echo $url | sed -e s,$userpass@,,g | cut -d/ -f1`
    port=`echo $hostport | grep : | cut -d: -f2`
    if [ -n "$port" ]; then
        host=`echo $hostport | grep : | cut -d: -f1`
    else
        host=$hostport
    fi

    echo "[INFO] host= "$host
    echo "[INFO] port= "$port

    # extract the path (if any)
    db_name="`echo $url | grep / | cut -d/ -f2-`"

    echo "[INFO] path= "$path

#    export JDBC_DATABASE_URL="jdbc:postgresql://${host}:${port}/${db_name}"
#    export JDBC_DATABASE_USERNAME=$user
#    export JDBC_DATABASE_PASSWORD=$pass
    export JDBC_DATABASE_URL="jdbc:postgresql://ec2-54-83-13-145.compute-1.amazonaws.com:5432/d27o33sup0mrf5"
    export JDBC_DATABASE_USERNAME="reztiufojlcqon"
    export JDBC_DATABASE_PASSWORD="0e5bb97ce08eaa44855dddefee0d939d0abfd2921c4a0349715889cdb0f29517"
else
   echo "[INFO] Using predefined JDBC_DATABASE_URL"
fi

/usr/bin/java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -Xmx256m -Xss512k -XX:MetaspaceSize=100m -jar /bin/app.jar