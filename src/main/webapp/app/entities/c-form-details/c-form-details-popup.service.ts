import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CFormDetails } from './c-form-details.model';
import { CFormDetailsService } from './c-form-details.service';

@Injectable()
export class CFormDetailsPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private cFormDetailsService: CFormDetailsService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.cFormDetailsService.find(id).subscribe((cFormDetails) => {
                    if (cFormDetails.dateOfBirth) {
                        cFormDetails.dateOfBirth = {
                            year: cFormDetails.dateOfBirth.getFullYear(),
                            month: cFormDetails.dateOfBirth.getMonth() + 1,
                            day: cFormDetails.dateOfBirth.getDate()
                        };
                    }
                    if (cFormDetails.passportExpirationDate) {
                        cFormDetails.passportExpirationDate = {
                            year: cFormDetails.passportExpirationDate.getFullYear(),
                            month: cFormDetails.passportExpirationDate.getMonth() + 1,
                            day: cFormDetails.passportExpirationDate.getDate()
                        };
                    }
                    if (cFormDetails.dateOfArrivalInIndia) {
                        cFormDetails.dateOfArrivalInIndia = {
                            year: cFormDetails.dateOfArrivalInIndia.getFullYear(),
                            month: cFormDetails.dateOfArrivalInIndia.getMonth() + 1,
                            day: cFormDetails.dateOfArrivalInIndia.getDate()
                        };
                    }
                    if (cFormDetails.visaEffectiveDate) {
                        cFormDetails.visaEffectiveDate = {
                            year: cFormDetails.visaEffectiveDate.getFullYear(),
                            month: cFormDetails.visaEffectiveDate.getMonth() + 1,
                            day: cFormDetails.visaEffectiveDate.getDate()
                        };
                    }
                    if (cFormDetails.visaExpiryDate) {
                        cFormDetails.visaExpiryDate = {
                            year: cFormDetails.visaExpiryDate.getFullYear(),
                            month: cFormDetails.visaExpiryDate.getMonth() + 1,
                            day: cFormDetails.visaExpiryDate.getDate()
                        };
                    }
                    this.ngbModalRef = this.cFormDetailsModalRef(component, cFormDetails);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.cFormDetailsModalRef(component, new CFormDetails());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    cFormDetailsModalRef(component: Component, cFormDetails: CFormDetails): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.cFormDetails = cFormDetails;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
