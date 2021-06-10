import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { SNACKBAR_CLOSE, SNACKBAR_ERROR, SNACKBAR_ERROR_OPTIONS } from '../constants/snackbar';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    public authService: AuthService,
    public userService: UserService,
    public router: Router,
    public snackBar: MatSnackBar
  ) { }

  loginPending = false;
  loginForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
    password: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))])
  });

  login(): void{
    if (this.loginForm.invalid){
      return;
    }
    this.loginPending = true;
      this.userService.login(this.loginForm.value).subscribe(
        (user: User) => {
          this.loginPending = false;
          if (user){
            this.authService.saveUser(user);
            this.router.navigate(['/home']);
          }
          else{
            this.snackBar.open("Pogresni email ili lozinka!", SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS);
          }
        }
      );
  } 

  ngOnInit(): void {
  }

}
