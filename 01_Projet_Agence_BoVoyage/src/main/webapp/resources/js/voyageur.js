/**
 * 
 */
var elementPattern = /^element(\d+)$/
var deletePattern = /^delete(\d+)$/
function ajouterElement()
{
        var Conteneur = document.getElementById('conteneur');
        if(Conteneur)
        {
                Conteneur.appendChild(creerElement(dernierElement() + 1))
        }
}

function dernierElement()
{
  var Conteneur = document.getElementById('conteneur'), n = 0;
  if(Conteneur)
  {
    var elementID, elementNo;
    if(Conteneur.childNodes.length > 0)
    {
      for(var i = 0; i < Conteneur.childNodes.length; i++)
      {
        // Ici, on vérifie qu'on peut récupérer les attributs, si ce n'est pas
		// possible, on renvoit false, sinon l'attribut
        elementID = (Conteneur.childNodes[i].getAttribute) ? Conteneur.childNodes[i].getAttribute('id') : false;
        if(elementID)
        {
          elementNo = parseInt(elementID.replace(elementPattern, '$1'));
          if(!isNaN(elementNo) && elementNo > n)
          {
            n = elementNo;
          }
        }
      }
    }
  }
  return n;
}

function creerElement(ID)
{
  var Conteneur = document.createElement('div');
  Conteneur.setAttribute('id', 'element' + ID);
  var Input = document.createElement('input');
  Input.setAttribute('type', 'text');
  Input.setAttribute('name', 'input' + ID);
  Input.setAttribute('id', 'input' + ID);
  var Delete = document.createElement('input');
  Delete.setAttribute('type', 'button');
  Delete.setAttribute('value', 'Supprimer n°' + ID + ' !');
  Delete.setAttribute('id', 'delete' + ID);
  Delete.onclick = supprimerElement;
  Conteneur.appendChild(Input);
  Conteneur.appendChild(Delete);
  return Conteneur;
}

function supprimerElement()
{
  var Conteneur = document.getElementById('conteneur');
  var n = parseInt(this.id.replace(deletePattern, '$1'));
  if(Conteneur && !isNaN(n))
  {
    var elementID, elementNo;
    if(Conteneur.childNodes.length > 0)
    {
      for(var i = 0; i < Conteneur.childNodes.length; i++)
      {
        elementID = (Conteneur.childNodes[i].getAttribute) ? Conteneur.childNodes[i].getAttribute('id') : false;
        if(elementID)
        {
          elementNo = parseInt(elementID.replace(elementPattern, '$1'));
          if(!isNaN(elementNo) && elementNo  == n)
          {
            Conteneur.removeChild(Conteneur.childNodes[i]);
            updateElements(); // A supprimer si tu ne veux pas la màj
            return;
          }
        }
      }
    }
  }  
}

function updateElements()
{
  var Conteneur = document.getElementById('conteneur'), n = 0;
  if(Conteneur)
  {
    var elementID, elementNo;
    if(Conteneur.childNodes.length > 0)
    {
      for(var i = 0; i < Conteneur.childNodes.length; i++)
      {
        elementID = (Conteneur.childNodes[i].getAttribute) ? Conteneur.childNodes[i].getAttribute('id') : false;
        if(elementID)
        {
          elementNo = parseInt(elementID.replace(elementPattern, '$1'));
          if(!isNaN(elementNo))
          {
            n++
            Conteneur.childNodes[i].setAttribute('id', 'element' + n);
            document.getElementById('input' + elementNo).setAttribute('name', 'input' + n);
            document.getElementById('input' + elementNo).setAttribute('id', 'input' + n);
            document.getElementById('delete' + elementNo).setAttribute('id', 'delete' + n);
          }
        }
      }
    }
  }
}