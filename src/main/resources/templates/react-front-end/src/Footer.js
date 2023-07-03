function Footer(){
    return(
    <>
      <div id="upper-footer" className="d-flex flex-row justify-content-evenly pt-5 border-top border-1">
            <div id="company info" className="d-flex flex-column ">
           <span className="mb-2"> 
          

           <img src="./logo.png" alt="" width="60" height="60" className="d-inline me-2"/>

           
           <h4  className="fw-bold p-0 d-inline">JavaGuidance</h4></span>
            <span className="mb-2"><i class="fa-solid fa-location-dot"></i> P.O Box 2174 Nairobi, Kenya</span>
            <span className="mb-3"><i class="fa-solid fa-envelope"></i> javaguides@gmail.com</span>
            <span style={{fontSize:"30px"}}  className="m-b d-flex flex-row justify-content-between">
            <i class="fa-brands fa-facebook"></i>
             <i class="fa-brands fa-instagram"></i>
             <i class="fa-brands fa-linkedin"></i>
             <i class="fa-brands fa-twitter"></i>
             {/* <i class="fa-brands fa-youtube"></i> */}
             </span>
            </div>
            
            <span className="vr"></span>

              <div id="about-us-pages" className="d-flex flex-column ">
              <span className="lead fw-bold">Company</span>
              <span>About Us</span>
              <span>Contact Us</span>
              <span>Adevertise With Us</span>
              <span>Legal</span>
            </div>

            <span className="vr"></span>

            <div id="explore" className="d-flex flex-column">
            <span className="lead fw-bold">Explore</span>
            <span>Java</span>
              <span>Linux</span>
              <span>Data Structures</span>
              <span>Front end </span>
</div>
      </div>
     <hr className="mt-5 mb-1 "></hr>
      <div id="lower-footer" className="pt-2 pb-2                                                                                                                                                                                                                                                                                                                                           " >
{/* ogo or  */} 
<div className="mb-1 text-decoration-underline">&copy; 2023 javaguidance.com</div>

</div>
    </>
    
    );
    }
    export default Footer;