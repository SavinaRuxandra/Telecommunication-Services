import {Component, OnInit} from '@angular/core';
import {ClientService} from "../shared/client.service";
import {ActivatedRoute, Router} from "@angular/router";
import {map} from "rxjs/operators";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Client} from "../shared/client.model";

@Component({
  selector: 'app-client-update',
  templateUrl: './client-update.component.html',
  styleUrls: ['./client-update.component.css']
})
export class ClientUpdateComponent implements OnInit {

  formGroup!: FormGroup;

  constructor(private clientService: ClientService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      id:  [{value: 'id', disabled:true}],
      cnp: new FormControl('', [Validators.required]),
      name: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      address: new FormControl('', [Validators.required])
    })

    this.activatedRoute.paramMap.pipe(map(() => window.history.state)).subscribe(client => {
      this.formGroup.get("id").setValue(client.id);
      this.formGroup.controls["cnp"].setValue(client.cnp);
      this.formGroup.controls["name"].setValue(client.name);
      this.formGroup.controls["email"].setValue(client.email);
      this.formGroup.controls["address"].setValue(client.address);
    });
  }

  updateClient() {
    const client = <Client>{
      id:this.formGroup.get("id").value,
      cnp:this.formGroup.get("cnp").value,
      name:this.formGroup.get("name").value,
      email:this.formGroup.get("email").value,
      address:this.formGroup.get("address").value
    }

    this.clientService.updateClient(client)
      .subscribe(() => this.router.navigate(['/clients']));
  }

}
