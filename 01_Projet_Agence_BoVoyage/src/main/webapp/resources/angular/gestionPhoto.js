


var monApp=angular.module("monApp",['naif.base64'])


	.controller("AjoutPhotoVoyage", function($scope,$http) {
		
		console.log("AjoutPhotoAngular")
		
		$scope.photo=null;		
		$scope.msg="Angular"
		$scope.i=0;
		
		$scope.listePhoto={
				
		}
			
			
		$scope.listePhoto=[]	
		
		
		$scope.affiche=function(){
			$scope.listePhoto[$scope.i]=$scope.photo.base64;
			$scope.i++;
		}
		
		
			
		$scope.valideImage=function(){
			// Recup attribut listePhoto de voyage
			console.log("Affectation")
			var element = document.getElementById("idCompagnieVoyage").value;
			console.log("element:"+element)
			element="LOL"
			$scope.msg=element
			
			var compagnie = document.getElementById("idCompagnieVoyage").path;
			console.log("compagnie:"+compagnie)
			//var imagesVoyageController = document.getElementById("listePhoto").value;
		}
		

		
		$scope.ajouterPhoto=function(){
			console.log("Ajout AJAX")
			var url='http://localhost:8080/01_Projet_Agence_BoVoyage/voyage/recupRest';
			var person = {"name":"John"};
			
			$http({
				method : 'POST',
				url :url,
				data : angular.toJson(person),
				header : {
					'content-type' : 'application/json'
				}
			}).then(
					function success(reponse) {
						// stockage de la réponse dans le callback
						
					},
					function erreur(reponse) {
						console.log("****erreur du serveur pour l'ajout de location: "
								+ reponse.status + " " + reponse.statusText)
					});
		}
	
	})