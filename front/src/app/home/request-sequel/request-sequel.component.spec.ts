import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestSequelComponent } from './request-sequel.component';

describe('RequestSequelComponent', () => {
  let component: RequestSequelComponent;
  let fixture: ComponentFixture<RequestSequelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestSequelComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestSequelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
