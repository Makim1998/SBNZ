import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(
    public authService: AuthService,
    public userService: UserService,
    public router: Router,
    public snackBar: MatSnackBar
  ) { }

  registerPending = false;
  registerForm: FormGroup = new FormGroup({
    ime: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
    prezime: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
    godine: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
    mesecna_zarada: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
    email: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
    password: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
    passwordConfirmation: new FormControl('', [Validators.required,
      Validators.pattern(new RegExp('\\S'))])
  });
  

  ngOnInit(): void {
  }

  register(): void{

  }

}
