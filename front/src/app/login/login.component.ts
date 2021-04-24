import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() { }
  loginPending = false;
  loginForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
    password: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))])
  });

  login(): void{
    this.loginPending = true;
  }
/*   login(): void{
    if (this.loginForm.invalid){
      return;
    }
    this.loginPending = true;
    this.userService.login(this.loginForm.value).subscribe(
      (user: User) => {
        this.loginPending = false;
        if (user){
          this.authService.saveUser(user);
          this.router.navigate(['/']);
        }
        else{
          this.snackBar.open(SNACKBAR_ERROR, SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS);
        }
      }
    );
  } */

  ngOnInit(): void {
  }

}
