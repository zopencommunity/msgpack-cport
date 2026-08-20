node('linux') {
  stage ('Poll') {
    checkout([
      $class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/MessagePackport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/MessagePackport.git'),
      string(name: 'PORT_DESCRIPTION', value: 'MessagePack implementation for C and C++ / msgpack.org[C/C++]'),
      string(name: 'BUILD_LINE', value: 'STABLE')
    ]
  }
}
