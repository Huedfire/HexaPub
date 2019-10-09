//console.log('correcto');
document.querySelector('#btn').addEventListener('click', traerDatos());
function traerDatos(){
  //  console.log('dentro de la funcion');
    const xhttp = new XMLHttpRequest();
    //abre el archivo
    xhttp.open('GET', 'file.json', true);
    xhttp.send();
    xhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
           // console.log(this.responseText);
            //separa datos json
            let datos = JSON.parse(this.responseText);
            //console.log(datos);
            let res = document.querySelector('#res');
            res.innerHTML= '';
            for(let item of datos){
                //console.log(item.artista);
                //combinando html para que escriba los datos en cada fila correspondiente
                res.innerHTML+=`
                    <tr>
                        <td>${item.action}</td>
                        <td>${item.data}</td>
                        <td>${item.parameters}</td>
                        <td>${item.status}</td>
                        <td>${item.request}</td>
                        <td>${item.response}</td>
                        <td>${item.description}</td>
                    </tr>
                `
            }
        }
    }
}