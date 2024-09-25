import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { CookieService } from 'ngx-cookie-service';
import { environment } from '../../../../environments/environment';
//import { SweetPortalService } from './sweet-portal.service';


@Injectable({
  providedIn: 'root'
})
export class AuthService {


  jwtHelper = inject(JwtHelperService);
  router = inject(Router);

  //dialogPortal = inject(SweetPortalService);

  URL = environment.URL

  constructor(
    private http: HttpClient, 
    private cookieService: CookieService
  ) {}


  public isAuthenticated(): boolean {
    return !this.jwtHelper.isTokenExpired(this.authToken);
  }

  get authToken(){
    return this.cookieService.get('authToken');
  }

  get userRole(){
    return "admin"
  }

  httpAccount(account:Partial<{
    userName: string | null;
    password: string | null;
    //passwordConfirm: string | null;
}>, type:string){
    return this.http.post<string>(`${this.URL}/auth/${type}`, account).subscribe({
      next: (res:any) => {
        this.cookieService.set("authToken", res.token);
        this.router.navigateByUrl("dashboard")
      },
      error: (err) => console.log(err)
    })
  }

  logout(){
    this.cookieService.delete("authToken");
    this.router.navigateByUrl("inicio");
  }

}