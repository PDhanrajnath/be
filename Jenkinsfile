import groovy.transform.Field

podTemplate(label: 'bc15-be', containers: [
	containerTemplate(name: 'bc15be-docker', image: 'docker:19.03', command: 'cat', ttyEnabled: true),
	containerTemplate(name: 'bc15-java', image: 'maven:3.8.1-adoptopenjdk-11', command: 'cat', ttyEnabled: true) ],
	volumes: [hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock')]
) {

  node('bc15-be'){
  	environment {
		docker_image=""
		DOCKERHUB_CREDENTIALS= credentials('Dhanrajnath_Docker')
		// MY_KUBECONFIG = credentials('config-file')
	}
	

// 		stage('Checkout Source') {
// 			steps {
//         git 'https://github.com/PDhanrajnath/fe.git'
//       }
//     }
	  
	  stage('Build Jar'){
	     git 'https://github.com/PDhanrajnath/be.git'
	           container('bc15-java'){
			   
	            		sh 'mvn -B -DskipTests clean package'
			   
	           }
	            
	    }
	  
	  

    
    stage('Build Docker'){
			
				
				container('bc15be-docker'){
					
				    stage('Build docker image') {
                
					sh 'docker build -t dhanrajnath/be_jenkins .'
					sh 'docker images'
					}
				}
			
		}
		
		stage('Push Docker'){
			
				container('bc15be-docker'){
					stage('Push docker image'){
					sh 'ls'
    withCredentials([usernamePassword(credentialsId: 'Dhanrajnath_Docker', usernameVariable: 'username', passwordVariable: 'password')]) {
						sh 'echo $PASSWORD'
                         sh 'docker login -u $username -p $password'
						echo USERNAME
						echo "username is $USERNAME"
						sh 'docker push dhanrajnath/be_jenkins'
    }           
				}
			}
		}


	
// 	post{
// 	    always{
// 	        container('bc15-docker'){
// 	         sh 'docker logout'
// 	    }
// 	    }
// 	}
  }

  


}





// pipeline{
	
// 	agent{
			
// 			kubernetes {
			    
// 			    yaml '''
//         apiVersion: v1
//         kind: Pod
//         spec:
//           containers:
//           - name: bc15-java
//             image: maven:3.8.1-adoptopenjdk-11
//             command:
//             - cat
//             tty: true
//           - name: bc15-docker
//             image: docker:19.03
//             command:
//             - cat
//             tty: true
//             volumeMounts:
//             - name: dockersock
//               mountPath: /var/run/docker.sock
//           volumes:
//             - name: dockersock
//               hostPath:
//                 path: /var/run/docker.sock
//         '''
    
//     }
// 	}
	
		
//     environment {
//         docker_image=""
//         DOCKERHUB_CREDENTIALS= credentials('Dhanrajnath_Docker')
//         MY_KUBECONFIG = credentials('config-file')
//     }
// 	stages{
// 	    stage('Checkout Source') {
//       steps {
//         git 'https://github.com/PDhanrajnath/be.git'
//       }
//     }
// 	    stage('Build Jar'){
	     
// 	        steps{
// 	           container('bc15-java'){
   
// 	            sh 'mvn -B -DskipTests clean package'

// 	           }
	            
// 	    }}
// 	    stage('Build Docker'){
//             steps{
//             container('bc15-docker'){

//             sh 'docker build -t dhanrajnath/be_jenkins .'
//             sh 'docker images'
            
//         }}  
// 	    }
	    
// 	   stage('Push Docker'){
// 	        steps{
// 	            container('bc15-docker'){
// 	            sh 'ls'
// 	            sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
// 	            sh 'docker push dhanrajnath/be_jenkins:latest'
	           
// 	        }
// 	        }
// 	    }
               
                    
				
// 	}    
// 	post{
// 	    always{
// 	        container('bc15-docker'){
// 	         sh 'docker logout'
// 	    }}
// 	}
               
                   		
// 	}
	
