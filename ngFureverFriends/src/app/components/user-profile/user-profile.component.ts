import { Image } from './../../models/image';
import { HttpClient } from '@angular/common/http';
import { Skill } from './../../models/skill';
import { AuthService } from 'src/app/services/auth.service';
import { Foster } from './../../models/foster';
import { FosterService } from './../../services/foster.service';
import { AccountService } from './../../services/account.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { Shelter } from 'src/app/models/shelter';
import { Account } from 'src/app/models/account';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  // Field
  user: User;
  isTheUserAFoster = false;
  admin: boolean;
  selectedUser: User;
  users: User[];
  shelter: Shelter;
  updateMessage: boolean;
  updateMessage2: boolean;
  showDetails: boolean;
  account: Account;
  foster: Foster;
  pass: string;
  form: FormGroup;
  isVolEmpty: boolean;
  isFosterActive = true;
  newSkillList = [];
  updateImage: Image = new Image();
  skills = [
    {
      id: 1,
      active: false
    },
    {
      id: 2,
      active: false
    },
    {
      id: 3,
      active: false
    },
    {
      id: 4,
      active: false
    },
    {
      id: 5,
      active: false
    },
    {
      id: 6,
      active: false
    }
  ]
  volBoolean = [
    {
      id: 1,
      name: 'Grooming'
    },
    {
      id: 2,
      name: 'Training'
    },
    {
      id: 3,
      name: 'General pet care'
    },
    {
      id: 4,
      name: 'Vet'
    },
    {
      id: 5,
      name: 'Customer Service'
    },
    {
      id: 6,
      name: 'General Maintenance & Repair'
    }
  ];

  constructor(private userService: UserService, private accountService: AccountService,
              private route: ActivatedRoute, private router: Router,
              private fostersvc: FosterService, private authSVC: AuthService,
              private cdRef: ChangeDetectorRef, private http: HttpClient) { }

  ngOnInit() {
    this.getUserPass();

    if (!this.user && this.route.snapshot.paramMap.get('id')) {
      this.userService.showOne(this.route.snapshot.paramMap.get('id')).subscribe(
        data => {
          this.user = data;

          this.checkedSkill();
          if (this.user === null) {
            this.router.navigateByUrl('user' + this.route.snapshot.paramMap.get('id') + 'NotFound');
          }

          // checks if user is shelter or not

          this.account = JSON.parse(localStorage.getItem('account'));
          // if (this.account.role === 'shelter') {
          //   this.showDetails = true;
          // }

          // checks if user is an admin
          if(this.account) {
            if (this.account.role === 'admin') {
              this.admin = true;
            }
          }

          // Checks if a user is a foster.  More efficent to create backend search by id.  Maybe in the future.

          this.fostersvc.index().subscribe(
            (pass) => {
              // this.account = JSON.parse(localStorage.getItem('account'));
              pass.forEach(element => {
                if (element.user.id === this.user.id) {
                  this.foster = element;
                  this.isTheUserAFoster = true;
                  if (this.foster.maxFoster <= 0) {
                    this.isFosterActive = false;
                  }
                }
              });
              if (this.isTheUserAFoster === false) {
                this.isFosterActive = false;
              }
            },
            err => {
              console.error('TodoListCompenent.loadtodoList():error loading todos');
            }
          );
        },
        err => console.error('failed to find user')
      );
    }
  }

  updateFoster() {
    if (this.foster.maxFoster <= 0) {
      this.foster.maxFoster = 0;
      this.isFosterActive = false;
    }
    this.fostersvc.update(this.foster).subscribe(
      data => {
        // TRIGGERS UPDATE MESSAGE
        this.updateMessage2 = false;
        this.cdRef.detectChanges();
        this.updateMessage2 = true;
      },
      error => {
        console.error('error updating foster');
      }
    );
  }

  getUserPass() {
    const a = (atob(this.authSVC.getCredentials()));  // converts to username:password
    const b = a.split(':'); // splits into an array of [username, password]
    const c = b[1]; // obtains password
    this.pass = c; // sets password for form and update
  }

  logout() {
    this.authSVC.logout();
  }

  login(username, password) {
    this.authSVC.login(username, password).subscribe(
      next => {

      },
      error => {
        console.error('error logging in.');
      }
    );
  }

  updateVolunteer() {


    this.newSkillList = [];
    this.skills.forEach(e => {
      if (e.active === true) {
        this.newSkillList.push(this.volBoolean[e.id - 1]);
      }
    });

    // if(this.user.skills.length === 0) {
    //   this.user.skills.push(this.newSkillList[0]);
    // } else {
      // COLLAPSES VOLUNTEER DIV WHEN THERE ARE NO SKILLS
    if (this.newSkillList.length === 0) {
        this.isVolEmpty = false;
      }

    this.user.skills = this.newSkillList;

    this.user.account.password = this.pass;

    this.userService.update(this.user.id, this.user).subscribe(
      data => {
        // LOGS OUT AND LOGS IN TO REFRESH CREDENTIALS
        this.logout();
        this.login(this.user.account.username, this.user.account.password);

        // TRIGGERS UPDATE MESSAGE
        this.updateMessage = false;
        this.cdRef.detectChanges();
        this.updateMessage = true;
      },
      error => {
        console.error('error updating volunteer');
      }
    );
  }

  checkedSkill() {
    this.skills.forEach(e => {
      this.user.skills.forEach(i => {
        if (i.id === e.id) {
          e.active = true;
          this.isVolEmpty = true;
        }
      });
    });
  }

  // Updates for the checkbox.
  volunterSwitch() {
    if(this.user.skills.length === 0) {
      this.skills[2].active = true;
      this.updateVolunteer();
    } else {
      if (this.isVolEmpty) {
        this.skills.forEach(e => {
          e.active = false;
        });
        this.updateVolunteer();
      } else {
        this.skills[2].active = true;
        this.updateVolunteer();
      }
    }
  }

  fosterSwitch() {
    if (this.isTheUserAFoster) {
      if (this.isFosterActive) {
        this.foster.maxFoster = 0;
        this.updateFoster();
      } else {
        this.foster.maxFoster = 1;
        this.updateFoster();
      }
    } else {
      const nf: Foster = {
          id: 1,
          maxFoster: 1,
          user: this.user,
          breedList: [],
          fosterPets: []
        };

      this.fostersvc.create(nf).subscribe(
        (pass) => {
          this.isFosterActive = true;
          this.isTheUserAFoster = true;
          this.foster = pass;
          this.cdRef.detectChanges();
        },
        (fail) => {
          console.error('error creating foster');
        }
      );
    }
  }

  updateUser() {
    // this.updateImage();
    if (this.newImage != null) {
      if (this.user.images.length === 0) {
        this.updateImage.imageUrl = this.newImage;
        this.user.images.push(this.updateImage);
      } else {
        this.user.images[0].imageUrl = this.newImage;
      }
    }

    this.user.account.password = this.pass;
    this.userService.update(this.user.id, this.user).subscribe(
      data => {
        this.logout();
        this.login(this.user.account.username, this.user.account.password);
       },
      error => {
        console.error('error updating user');
      }
    );
  }

  /// Image upload
  selectedFile: File = null;
  newImage: string;

  onFileSelected(event) {
    this.selectedFile = event.target.files[0] as File;
    const fd = new FormData();
    fd.append('image', this.selectedFile, this.selectedFile.name);

    this.http.post<ImageResponse>('https://api.imgbb.com/1/upload?key=5eaa21d03c3d50fc34483bccfbea594f', fd).subscribe(
      res => {
        this.newImage = res.data.url;
      }
    );
  }
}

interface ImageResponse {
  data: any;
}
