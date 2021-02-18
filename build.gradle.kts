plugins {
    id("org.danilopianini.git-sensitive-semantic-versioning")
}

group = "io.github.enrignagna"

gitSemVer {
    version = computeGitSemVer()
}

repositories {
    jcenter()
}


