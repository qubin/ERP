(function($, window, undefined){
    var globals = {
		'timeoutID':       null,// This variable will hold the timeout ID
		'timeoutCanceled': false,// We'll use this variable to know if the timeout was canceled or not (so we know if we have to reset it or not).
        'options':         {}
	};
    
	var methods = {
		init : function( options ) {
			var defaults = {
				'leftArrowSelector':    '.slider-nav.left',
				'rightArrowSelector':   '.slider-nav.right',
				'slideWrapperSelector': '.slide-wrapper',
				'slideClass':           'slide',
				'activeSlideClass':     'active',
				'arrowKeysNavigate':    true,
				'animationType':        'fade',// supports 'fade' for now
				'animationSpeed':       'fast',
				'onAnimationComplete':  $.noop,
				'autoStartSlideshow':   true,
				'slideTimeoutMilliseconds': 3500,
				'dataSlide':            'slide'// data-* property to check for the JSON object with slice specific options (animationType, animationSpeed)
			};

			options = $.extend( defaults, options );
            
            globals.options = options;

			// Bind Arrow Keys Navigation
			if ( options.arrowKeysNavigate ) {
				$(document).on( 'keydown.slider', function( event ) {
					if ( event.keyCode === 37 ) {// Left
						event.preventDefault();

						methods.navigateLeft.call( this, options );
					} else if ( event.keyCode === 39 ) {// Right
						event.preventDefault();

						methods.navigateRight.call( this, options );
					}
				});
			}

			return this.each(function() {
				var $this = $(this),
					data = $this.data( 'slider' );

				if ( ! data ) {
					$this.data( 'slider', {
						target : $this
					});

					// Bind Arrow Buttons Navigation
					$this.find( options.leftArrowSelector ).on( 'click.slider', function( event ) {
						event.preventDefault();

						methods.navigateLeft.call( this, options );

					});

					$this.find( options.rightArrowSelector ).on( 'click.slider', function( event ) {
						event.preventDefault();

						methods.navigateRight.call( this, options );

					});
                    
                    // Start automatically if the setting is set to do so
					if ( options.autoStartSlideshow ) {
						methods.navigateTo.call( this, options, 0 );
						globals.timeoutID = window.setTimeout(function() {
							methods.doLoop.call( this, options );
						}, options.slideTimeoutMilliseconds );
					} else {
						globals.timeoutCanceled = true;
					}

				}
			});
		},

		destroy : function() {
			$(window).off( '.slider' );

			return this.each(function(){
				var $this = $(this),
					data = $this.data( 'slider' );

				$this.removeData( 'slider' );
			});
		},
        
        getOptions : function() {
			return globals.options;
		},
        
        // Method to show slide
		showSlide: function( options, slideIndex ) {
			var slides = $(options.slideWrapperSelector).find( '.' + options.slideClass ),
				currentSlide = slides.siblings( '.' + options.activeSlideClass ),
				currentIndex = currentSlide.index(),
				$this = $( options.slideWrapperSelector ).find( '.' + options.slideClass + ':eq(' + slideIndex + ')' );

			var itemDefaults = {
				'animationType':  options.animationType,
				'animationSpeed': options.animationSpeed
			};

			// Extend the default item options using data-* attribute
			var itemOptions = $.extend(itemDefaults, $this.data( options.dataSlide ));

			switch ( itemOptions.animationType ) {
				case 'fade':
				default:
					// Hide previous slide
					currentSlide.fadeOut( itemOptions.animationSpeed, function() {
						$(this).removeClass( options.activeSlideClass );
					});

					// Do animation
					$this.fadeIn( itemOptions.animationSpeed, function() {
						$(this).addClass( options.activeSlideClass );

						// Trigger onAnimationComplete
						if ( $.isFunction(options.onAnimationComplete) ) {
							options.onAnimationComplete.call( this, options, itemOptions, slideIndex );
						}

						// Show arrows if there is navigation
						methods.showOrHideArrows.call( this, options );
                        
                        // Keep sliding automatically if the setting is set to do so
						if ( ! globals.timeoutCanceled ) {
							globals.timeoutID = window.setTimeout(function() {
								methods.doLoop.call( this, options );
							}, options.slideTimeoutMilliseconds);
						}
					});
				break;
			}
		},

		// Show arrows if there is navigation
		showOrHideArrows: function( options ) {
			var slides = $(options.slideWrapperSelector).find( '.' + options.slideClass ),
				currentIndex = slides.siblings( '.' + options.activeSlideClass ).index();
			if ( slides.length > 1 ) {
				if ( currentIndex >= 0 ) {
					// Show arrows
					$( options.slideWrapperSelector ).siblings( options.leftArrowSelector + ', ' + options.rightArrowSelector ).show();

					// Is it the last slide?
					if ( currentIndex == (slides.length - 1) ) {
						// Hide right arrow
						$( options.slideWrapperSelector ).siblings( options.rightArrowSelector ).hide();
					}

					// Is it the first slide?
					if ( currentIndex === 0 ) {
						// Hide left arrow
						$( options.slideWrapperSelector ).siblings( options.leftArrowSelector ).hide();
					}
				}
			}
		},

		// Method to navigate to a slide
		navigateTo: function( options, calledIndex ) {
			var slides = $(options.slideWrapperSelector).find( '.' + options.slideClass ),
				currentIndex = slides.siblings( '.' + options.activeSlideClass ).index();
			if ( calledIndex != currentIndex ) {
				methods.showSlide.call( this, options, calledIndex );
			}
		},

		// Method to navigate "left"
		navigateLeft: function( options ) {
			var slides = $(options.slideWrapperSelector).find( '.' + options.slideClass ),
				currentIndex = slides.siblings( '.' + options.activeSlideClass ).index();

			if ( slides.length > 0 ) {
				var newIndex = ( currentIndex - 1 );

				// If we're already on the first slide, do nothing
				if ( newIndex < 0 ) {
					return false;
				}
                
                // If the navigation was triggered, we need to cancel the slideshow timeout
				methods.cancelTimeout.call( this, options );

				methods.navigateTo.call( this, options, newIndex );
			}
		},

		// Method to navigate "right"
		navigateRight: function( options ) {
			var slides = $(options.slideWrapperSelector).find( '.' + options.slideClass ),
				currentIndex = slides.siblings( '.' + options.activeSlideClass ).index();

			if ( slides.length > 0 ) {
				var newIndex = ( currentIndex + 1 );

				// If we're already on the last slide, do nothing
				if ( newIndex > (slides.length - 1) ) {
					return false;
				}
                
                // If the navigation was triggered, we need to cancel the slideshow timeout
				methods.cancelTimeout.call( this, options );

				methods.navigateTo.call( this, options, newIndex );
			}
		},

		// Add slideshow method (looping)
		doLoop: function( options ) {
			var slides = $(options.slideWrapperSelector).find( '.' + options.slideClass ),
				currentIndex = slides.siblings( '.' + options.activeSlideClass ).index();

			// There's no need to loop if there's only 1 slide
			if ( slides.length > 1 ) {
				var newIndex = ( currentIndex + 1 );

				// If we're already on the last slide, go to the first
				if ( newIndex > (slides.length - 1) ) {
					newIndex = 0;
				}

				methods.navigateTo.call( this, options, newIndex );
			}
		},

		// Cancel the slideshow timeout
		cancelTimeout: function( options ) {
			if ( globals.timeoutID ) {
				window.clearTimeout( globals.timeoutID );
			}

			globals.timeoutCanceled = true;
		}
	};

	$.fn.slider = function( method ) {
		if ( methods[method] ) {
			return methods[method].apply( this, Array.prototype.slice.call(arguments, 1) );
		} else if ( typeof method === 'object' || ! method ) {
			return methods.init.apply( this, arguments );
		} else {
			$.error( 'Method ' +  method + ' does not exist on jQuery.slider' );
		}
	};
})( jQuery, window );

jQuery(document).ready(function($) {
	//$('.slider').slider();
	$('.slider').slider({
		'autoStartSlideshow': true,
		'onAnimationComplete': function( options, itemOptions, slideIndex ) {
			$('.slider .slider-bottom-nav span').removeClass( 'active' );
			$('.slider .slider-bottom-nav span:eq(' + slideIndex + ')').addClass( 'active' );
		}
	});

	$('.slider .slider-bottom-nav span').on( 'click.app', function( event ) {
		event.preventDefault();

		var navIndex = window.parseInt( $(this).data('slide'), 10 );

		$('.slider').slider( 'navigateTo', $('.slider').slider('getOptions'), navIndex );
		$('.slider').slider( 'cancelTimeout' );

	});

});