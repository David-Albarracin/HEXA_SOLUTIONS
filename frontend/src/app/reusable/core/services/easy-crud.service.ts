import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EasyCrudService {

  URL = environment.URL

  constructor(
    private http: HttpClient, 
  ) {}

  httpGetList = (moduleName: string, customURL?: string, params?: any[]) => {
    // Construcción de la URL base
    let url = `${this.URL}/${moduleName}${customURL ? '/' + customURL : ''}`;
  
    // Si se proporcionan parámetros, construimos el query string
    if (params) {
      for (let index = 0; index < params.length; index++) {
        const queryString = new URLSearchParams(params[index]).toString();
          url += `?${queryString}`;
      }
    }
  
    // Realizamos la solicitud HTTP GET
    return this.http.get<[]>(url);
  }

  httpGetById = (moduleName: string, id:string) => {
    return this.http.get<{}>(`${this.URL}/${moduleName}/${id}`)
  }

  httpDeleteById = (moduleName: string, id:string) => {
    return this.http.delete<{}>(`${this.URL}/${moduleName}/${id}`)
  }

  httpCreate = (moduleName: string, data:any) => {
    console.log(data);
    
    return this.http.post<{}>(`${this.URL}/${moduleName}`, data)
  }

  httpUpdate= (moduleName: string, id:string, data:any) => {
    console.log(data);

    return this.http.put<{}>(`${this.URL}/${moduleName}/${id}`, data)
  }

  httpAccount(type:string, account:any){
    return this.http.post<{}>(`${this.URL}/account/${type}`, account)
  }



}
