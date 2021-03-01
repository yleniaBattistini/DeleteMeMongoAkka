#!/bin/bash
set -e

if [ -x 'gradlew' ]; then
    echo 'Detected gradle wrapper, checking for known tasks'
    if ./gradlew tasks | grep '^check\s'; then
        echo 'Detected check task'
        ./gradlew check --parallel
        if ./gradlew tasks | grep '^jacocoTestReport\s'; then
            ./gradlew jacocoTestReport --parallel
        fi
    else
        echo 'No known check tasks'
    fi
else
    echo 'No valid configuration detected, skipping checks'
    #echo "To fix, provide an *executable* build script in $CUSTOM_BUILD_SCRIPT"
fi
