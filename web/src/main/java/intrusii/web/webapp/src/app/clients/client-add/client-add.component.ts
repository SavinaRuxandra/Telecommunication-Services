import {Component, Input, OnInit} from '@angular/core';
import {Client} from "../shared/client.model";
import {ClientService} from "../shared/client.service";
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
  Validators
} from "@angular/forms";
import {Router, RouterLink} from "@angular/router";
import {ValidateFn} from "codelyzer/walkerFactory/walkerFn";
import NumberFormat = Intl.NumberFormat;
import {NUMBER_TYPE} from "@angular/compiler/src/output/output_ast";

@Component({
  selector: 'app-client-add',
  templateUrl: './client-add.component.html',
  styleUrls: ['./client-add.component.css']
})
export class ClientAddComponent implements OnInit {

  @Input() client!: Client;
  formGroup!: FormGroup;

  constructor(private clientService: ClientService,
              private formBuilder: FormBuilder,
              private router: Router) { }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      cnp: new FormControl('', [Validators.required]),
      name: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      address: new FormControl('', [Validators.required])
    })
  }

  addClient(): void {
    const client = <Client>{
      cnp:this.formGroup.controls["cnp"].value,
      name:this.formGroup.controls["name"].value,
      email:this.formGroup.controls["email"].value,
      address:this.formGroup.controls["address"].value
    }
    this.clientService.addClient(client).subscribe(() => {
      this.router.navigate(['/clients']);
    });
  }
}
