import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PetprofComponent } from './petprof.component';

describe('PetprofComponent', () => {
  let component: PetprofComponent;
  let fixture: ComponentFixture<PetprofComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PetprofComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PetprofComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
