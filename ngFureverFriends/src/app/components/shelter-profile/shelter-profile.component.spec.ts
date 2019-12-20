import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShelterProfileComponent } from './shelter-profile.component';

describe('ShelterProfileComponent', () => {
  let component: ShelterProfileComponent;
  let fixture: ComponentFixture<ShelterProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShelterProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShelterProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
