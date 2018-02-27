import { Injectable } from '@angular/core';

import { JwtHelper } from 'angular2-jwt';

import { TOKEN_NAME } from '../../constants/auth.constants';

@Injectable()
export class UserService {
  jwtHelper: JwtHelper = new JwtHelper();
  username: string;
  accessToken: string;
  isAdmin: boolean = false;
  isParent: boolean = false;
  isProvider: boolean = false;
  role: string;
  providerCompanyName: string;

  constructor() {
  }

  login(accessToken: string) {
    const decodedToken = this.jwtHelper.decodeToken(accessToken);
    console.log(decodedToken);

    this.username = decodedToken.user_name;

    this.isAdmin     = decodedToken.authorities.some(el => el === 'ADMIN');
    this.isParent    = decodedToken.authorities.some(el => el === 'PARENT');
    this.isProvider  = decodedToken.authorities.some(el => el === 'PROVIDER');
    this.accessToken = accessToken;
    this.role = decodedToken.authorities[0];
    if(this.role == 'PROVIDER')
        this.providerCompanyName = decodedToken.authorities[1];

    localStorage.setItem(TOKEN_NAME, accessToken);
  }

  logout() {
    this.accessToken = null;
    this.isAdmin = false;
    this.isParent = false;
    this.isProvider = false;
    this.role = null;
    this.username = null;
    this.providerCompanyName = null;
    localStorage.removeItem(TOKEN_NAME);
  }

  isAdminUser(): boolean {
    return this.isAdmin;
  }

  isParentUser(): boolean {
    return this.isParent;
  }

  isProviderUser(): boolean {
    return this.isProvider;
  }
  isUser(): boolean {
    return (this.isProvider||this.isParent)
  }

  getUsername(): string {
    return this.username;
  }

  getAccessToken(): string {
    return this.accessToken;
  }

  getrole(): string {
    return this.role;
  }

  getProviderCompanyName() :string {
    return this.providerCompanyName;    
  }


}
