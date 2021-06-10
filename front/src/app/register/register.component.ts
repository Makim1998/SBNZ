import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS, SNACKBAR_SUCCESS_OPTIONS } from '../constants/snackbar';
import { User } from '../models/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(
    public userService: UserService,
    public router: Router,
    public snackBar: MatSnackBar
  ) { }

  registerPending = false;
  registerForm: FormGroup = new FormGroup({
    firstName: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
    lastName: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
    godine: new FormControl('', [Validators.required,
    Validators.pattern('^[1-9]{1}[0-9]{0,1}$')]),
    mesecna_zarada: new FormControl('', [Validators.required,
    Validators.pattern('^[1-9]{1}[0-9]*$')]),
    email: new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$'),
    Validators.pattern(new RegExp('\\S'))]),
    password: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
    passwordConfirmation: new FormControl('', [this.passwordConfirmed()])
  });
  

  ngOnInit(): void {
  }

  passwordConfirmed(): ValidatorFn{
    return (control: AbstractControl): ValidationErrors => {
      const passwordConfirmed: boolean = control.parent ?
      control.value === control.parent.get('password').value : true;
      return passwordConfirmed ? null : {passwordError: true};
    };
  }

  register(): void{
    if (this.registerForm.invalid){
      return;
    }
    this.registerPending = true;
    this.userService.register(this.registerForm.value).subscribe(
        (user: User) => {
          this.registerPending = false;
          if (user){
            this.snackBar.open("Uspesno ste se registrovali!", SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS);

          }
          else{
            this.snackBar.open("Korisnicko ime je zauzeto!", SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS);
          }
        }
      );
  }

}
