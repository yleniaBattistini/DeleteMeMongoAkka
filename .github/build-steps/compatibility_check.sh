#!/bin/bash
if [ -x .github/scripts/compatibility_check ]; then
    .github/scripts/compatibility_check
    echo "this"
else
    COMPATIBLE=true
    echo "COMPATIBLE1=$COMPATIBLE"
    echo "COMPATIBLE=$COMPATIBLE" >> $GITHUB_ENV
    REFERENCE=$([ "$OS" = 'ubuntu' ] && [ "$JAVA_VERSION" = 8 ] && echo 'true' || echo 'false')
    echo "REFERENCE=$REFERENCE"
    echo "REFERENCE=$REFERENCE" >> $GITHUB_ENV
fi
