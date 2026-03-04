$(document).ready(function(){
    let slider = $('.bxslider').bxSlider({
        auto: true,
    });

    $('.slider-basic').slick({
        autoplay: true,
        dots: true,
        arrows: false,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1
    });

    var $mobileToggle = $('.mobile-menu-toggle');
    var $navigation = $('.main-navigation');
    var $body = $('body');

    function openMobileMenu() {
        $mobileToggle.addClass('active');
        $mobileToggle[0].setAttribute('aria-expanded', 'true');
        $navigation.addClass('active');
        $body.css('overflow', 'hidden');
    }

    function closeMobileMenu() {
        $mobileToggle.removeClass('active');
        if ($mobileToggle[0]) {
            $mobileToggle[0].setAttribute('aria-expanded', 'false');
        }
        $navigation.removeClass('active');
        $body.css('overflow', '');
        
        $('.menu-item').removeClass('active');
        $('.submenu-toggle').each(function() {
            this.setAttribute('aria-expanded', 'false');
        });
    }

    $mobileToggle.off('click').on('click', function(e) {
        e.preventDefault();
        e.stopPropagation();
        e.stopImmediatePropagation();
        
        var $this = $(this);
        var isActive = $this.hasClass('active');
        
        if (isActive) {
            closeMobileMenu();
        } else {
            openMobileMenu();
        }
        
        return false;
    });


    $(document).on('keydown', function(e) {
        if (e.key === 'Escape' && $mobileToggle.hasClass('active')) {
            closeMobileMenu();
        }
    });

    $('.submenu-toggle').on('click', function(e) {
        e.preventDefault();
        e.stopPropagation();
        
        var $toggle = $(this);
        var toggleEl = this;
        var $menuItem = $toggle.closest('.menu-item');
        var isExpanded = toggleEl.getAttribute('aria-expanded') === 'true';
        
        $('.menu-item').not($menuItem).removeClass('active');
        $('.submenu-toggle').not($toggle).each(function() {
            this.setAttribute('aria-expanded', 'false');
        });
        
        if (isExpanded) {
            $menuItem.removeClass('active');
            toggleEl.setAttribute('aria-expanded', 'false');
        } else {
            $menuItem.addClass('active');
            toggleEl.setAttribute('aria-expanded', 'true');
        }
    });

   
    $('.menu-link').on('click', function(e) {
        const $menuItem = $(this).closest('.menu-item');
        const hasSubmenu = $menuItem.hasClass('has-submenu');
      
        if ($(window).width() <= 1024 && hasSubmenu) {
            e.preventDefault();
            const $toggle = $menuItem.find('.submenu-toggle');
            $toggle.trigger('click');
        }
    });

    let resizeTimer;
    $(window).on('resize', function() {
        clearTimeout(resizeTimer);
        resizeTimer = setTimeout(function() {
            if ($(window).width() > 1024) {
                closeMobileMenu();
            }
        }, 250);
    });

    
    $mobileToggle.on('focus', function() {
        $(this).css('outline', '2px solid #003f8c');
    }).on('blur', function() {
        $(this).css('outline', 'none');
    });

    $('.submenu-toggle').on('focus', function() {
        $(this).css('outline', '2px solid #003f8c');
    }).on('blur', function() {
        $(this).css('outline', 'none');
    });
});
